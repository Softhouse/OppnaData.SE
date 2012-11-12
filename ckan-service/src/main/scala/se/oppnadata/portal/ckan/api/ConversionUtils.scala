package se.oppnadata.portal.ckan.api
import org.joda.time.DateTime
import org.joda.time.LocalDate

object ConversionUtils {

  def toDateTime(str : String) : DateTime = {
    if ( str != null ) {
      return new DateTime(str)
    }
    else return null
  }
  
  def toLocalDate(str : String) : LocalDate = {
    val dateTime = toDateTime(str)
    if ( dateTime != null ) {
      return dateTime.toLocalDate()
    }
    else return null
  }
}