package se.oppnadata.portal.ckan.api
import scala.reflect.BeanInfo
import scala.annotation.target.field
import java.util.Date
import sjson.json.JSONTypeHint
import org.joda.time.LocalDateTime
import org.joda.time.DateTime
import sjson.json.JSONProperty
import scala.annotation.target.getter
import scala.annotation.target.setter
import collection.JavaConversions._

// TODO: Immutable?? Then we need a conversion layer for JSON objects

/**
 * Dataset value object
 */
@BeanInfo
class Dataset {

	// TODO: How to decode dates???
  
	var id : String = null
	var name : String = null
	var title : String = null
	var version : String = null
	var revision_id : String = null
	var url : String = null
	var ckan_url : String = null
	var notes : String = null
	var notes_rendered : String = null
	var isopen = false
	
	var maintainer : String = null
	var maintainer_email : String = null
	
	var metadata_created : String = null
	def metadata_created_date = ConversionUtils.toLocalDate(metadata_created)
	var metadata_modified : String = null
	def metadata_modified_date = ConversionUtils.toLocalDate(metadata_modified)
	
	
	// var relationships - TODO: Add support for relationships
	var author : String = null
	var author_email: String = null
	var license : String = null
	var licence_id : String = null
	var download_url : String = null
	var state : String = null	
	var ratings_count : BigDecimal = null
	var ratings_average : BigDecimal = null
	
	var tags : List[String] = null
	def tagArray : java.util.List[String] = tags.toBuffer[String]
	
	var groups : List[String] = null
	var extras : Map[String,String] = null
	def extrasMap : java.util.Map[String,String] = extras.toMap[String,String]
	
	@(JSONTypeHint @field)(value = classOf[Resource])
	var resources : List[Resource] = null
	
	def resourceArray : java.util.List[Resource] = resources.toBuffer[Resource]
	
}