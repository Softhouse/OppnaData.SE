package se.oppnadata.portal.ckan.service
import se.oppnadata.portal.ckan.api.CKANService
import se.oppnadata.portal.ckan.api.Dataset
import dispatch._
import sjson.json.Serializer.SJSON
import scala.reflect.BeanInfo
import scala.collection.mutable.WeakHashMap
import sjson.json.JSONTypeHint
import scala.annotation.target.field
import scala.collection.mutable.HashMap
import com.gu.cache.simplecache.SoftReferenceSimpleCache
import com.gu.cache.simplecache.SimpleCache
import java.util.concurrent.TimeUnit
import se.oppnadata.portal.ckan.api.NotFoundException
import se.oppnadata.portal.ckan.api.CommunicationException

/**
 * CKAN Service implementation
 */
class CKANServiceBean extends CKANService  {

  // TODO: Add error handling here...
 
  /**
   * Cache implicit functions
   */
  implicit def cacheWrapper(cache: SimpleCache) = new {
	  def getValue[T](key: String): Option[T] = {         
	     Option(cache.get(key).asInstanceOf[T])	 
	  }
	  
	  def putValue[T](key: String, value: T) : T = {
	    cache.putWithExpiry(key, value, ckanCatalogCacheExpiryTime, TimeUnit.MINUTES)
	    value
	  }
  }
  
  /**
   * Cache
   */
  var cache = new SoftReferenceSimpleCache;
  
  /**
   * Service properties
   */
  var ckanCatalogHost : String = ""; 
  var ckanCatalogCacheExpiryTime : Int = 5;
  
  def setCkanCatalogHost(host : String) {
    ckanCatalogHost = host;
  }
  
  def setCkanCatalogCacheExpiryTime(time : Int) {
    ckanCatalogCacheExpiryTime = time;
  }
  
  /**
   * HTTP
   */
  val http = new Http  
  
  /**
   * Service methods
   */
  def getAllTags : List[String] = {
	return SJSON.in[List[String]](getJsonResponse("/api/rest/tag"))
  }
  
  def getAllDatasets : List[String] = {
    return SJSON.in[List[String]](getJsonResponse("/api/rest/dataset"))
  }
  
  def getDataset(name : String) : Dataset = {
    
    cache.getValue[Dataset](name).getOrElse(
        cache.putValue[Dataset](name, 
            SJSON.in[Dataset](getJsonResponse("/api/rest/dataset/" + name))))
  }
  
  def searchDatasetsByTags(tags : List[String]) : List[Dataset] = {
    var searchResult = searchDatasets(tags.map(tag => "tags=" + tag).mkString("&"))
    return searchResult.results
  }
  
  def searchDatasetIdsByTag(tag : String) : List[String] = {
    
    val cacheId = "TAG:" + tag;
    
    cache.getValue[List[String]](cacheId).getOrElse(
        cache.putValue[List[String]](cacheId, 
            SJSON.in[StringSearchResult](getJsonResponse("/api/search/dataset?tags=" + tag + "&limit=1000")).results))

  }
  
  /**
   * Internal methods
   */
  protected def getJsonResponse(resourceUri : String) = {
    try {
    	http(:/(ckanCatalogHost) / resourceUri >~ { _.getLines.mkString })
    }
    catch {
      case e : StatusCode => throwError(e.code)
    }
  }
  
  protected def throwError(code : Int) = {
    code match {
      case 404 => throw new NotFoundException
      case _ => throw new CommunicationException
    }
  }
  
  protected def searchDatasets(searchString : String) : SearchResult = {
    var jsonResponse = getJsonResponse("/api/search/dataset?" + searchString + "&all_fields=1")
    return SJSON.in[SearchResult](jsonResponse)
  }
}

/**
 * Value object representation of various JSON search results
 */
@BeanInfo
class SearchResult {

  var count : BigDecimal = null
  @(JSONTypeHint @field)(value = classOf[Dataset])
  var results : List[Dataset] = null
}

class StringSearchResult {
  var count : BigDecimal = null
  @(JSONTypeHint @field)(value = classOf[String])
  var results : List[String] = null
}

