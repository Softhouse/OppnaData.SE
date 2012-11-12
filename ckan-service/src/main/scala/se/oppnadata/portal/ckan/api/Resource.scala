package se.oppnadata.portal.ckan.api
import scala.reflect.BeanInfo

/**
 * Resource value object
 */
@BeanInfo
class Resource {

  var id : String = null
  var name : String = null
  var description : String = null
  var url : String = null
  var content_length : String = null
  var size : String = null
  var resource_group_id : String = null
  var hash : String = null
  var format : String = null
  var package_id : String = null
  var mimetype : String = null
  var mimetype_inner : String = null
  var content_type : String = null
  var openness_score_reason : String = null
  var cache_url : String = null
  var cache_last_updated : String = null
  def cache_last_updated_date = ConversionUtils.toDateTime(cache_last_updated)
  var webstore_url : String = null
  var webstore_last_updated : String = null
  def webstore_last_updated_date = ConversionUtils.toDateTime(webstore_last_updated)
  var last_modified : String = null 
  def last_modified_date = ConversionUtils.toDateTime(last_modified)
  var position : BigDecimal = null
  var resource_type : String = null
  var openness_score : String = null
  var openness_score_failure_count : String = null
  
}