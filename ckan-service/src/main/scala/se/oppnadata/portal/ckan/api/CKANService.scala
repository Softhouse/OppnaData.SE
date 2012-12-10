package se.oppnadata.portal.ckan.api

/**
 * Published CKAN Service interface 
 */
trait CKANService {

  /**
   * Get all available tags defined in the CKAN catalog
   */
  @throws(classOf[CKANException])
  def getAllTags : List[String];
  
  /**
   * Get all available datasets defined in the CKAN catalog
   */
  @throws(classOf[CKANException])
  def getAllDatasets : List[String];
  
  /**
   * Get a dataset with specified ID
   */
  @throws(classOf[CKANException])
  def getDataset(name : String) : Dataset;
  
  /**
   * Find all datasets matching a list of tags
   */
  @throws(classOf[CKANException])
  def searchDatasetsByTags(tags : List[String]) : List[Dataset];
  
  /**
   * Find all dataset IDs matching specified ID
   */
  @throws(classOf[CKANException])
  def searchDatasetIdsByTag(tag : String) : List[String];
}

/**
 * CKAN Exception
 */
abstract class CKANException extends Exception 
class NotFoundException extends CKANException
class CommunicationException extends CKANException
