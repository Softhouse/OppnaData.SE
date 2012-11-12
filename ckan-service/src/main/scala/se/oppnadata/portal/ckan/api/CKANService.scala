package se.oppnadata.portal.ckan.api

/**
 * Published CKAN Service interface 
 */
trait CKANService {

  def getAllTags : List[String];
  
  def getAllDatasets : List[String];
  
  def getDataset(name : String) : Dataset;
  
  def searchDatasetsByTags(tags : List[String]) : List[Dataset];
  
  def searchDatasetIdsByTag(tag : String) : List[String];
}