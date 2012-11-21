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

/**
 * CKAN Service implementation
 */
class CKANServiceBean extends CKANService  {

  // TODO: Add error handling here...
  // TODO: Add cache support here!!
  
  // Temporary cache solution TODO: Implement a better approach for this
  //
  var datasetCache = new /*Weak*/ HashMap[String,Dataset]; // TODO: Have the cache not clearable for demo purposes
  var datasetsByTagCache = new HashMap[String, List[String]];
  
  var ckanCatalogHost : String = ""; 
  
  def setCkanCatalogHost(host : String) {
    ckanCatalogHost = host;
  }
  
  val http = new Http  
  
  def getAllTags : List[String] = {
	return SJSON.in[List[String]](getJsonResponse("/api/rest/tag"))
  }
  
  def getAllDatasets : List[String] = {
    return SJSON.in[List[String]](getJsonResponse("/api/rest/dataset"))
  }
  
  def getDataset(name : String) : Dataset = {
    datasetCache.getOrElseUpdate(name, SJSON.in[Dataset](getJsonResponse("/api/rest/dataset/" + name))) 
  }
  
  def searchDatasetsByTags(tags : List[String]) : List[Dataset] = {
    var searchResult = searchDatasets(tags.map(tag => "tags=" + tag).mkString("&"))
    return searchResult.results
  }
  
  def searchDatasetIdsByTag(tag : String) : List[String] = {
    datasetsByTagCache.getOrElseUpdate(tag, SJSON.in[StringSearchResult](getJsonResponse("/api/search/dataset?tags=" + tag + "&limit=1000")).results)
    //val jsonResponse = getJsonResponse("/api/search/dataset?tags=" + tag + "&limit=1000")
    //val result = SJSON.in[StringSearchResult](jsonResponse)
    //return result.results
  }
  
  protected def getJsonResponse(resourceUri : String) = http(:/(ckanCatalogHost) / resourceUri >~ { _.getLines.mkString })
  
  protected def searchDatasets(searchString : String) : SearchResult = {
    var jsonResponse = getJsonResponse("/api/search/dataset?" + searchString + "&all_fields=1")
    return SJSON.in[SearchResult](jsonResponse)
  }
}

// TODO: How to solve this with generics??
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