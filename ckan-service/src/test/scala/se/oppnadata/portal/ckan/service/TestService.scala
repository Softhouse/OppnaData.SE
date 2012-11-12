package se.oppnadata.portal.ckan.service
import se.oppnadata.portal.ckan.api.Dataset

// TODO: Make this a unit test class instead! Use Spec?

object TestService extends App {

  implicit def convertScalaListToJavaList(aList:List[String]) = java.util.Arrays.asList(aList.toArray: _*)

  
  val ckanService = new CKANServiceBean
  ckanService.ckanCatalogHost = /*"test.ckan.org" */"data.gov.uk"
  
  
  /*
  println("Defined tags:")
  for ( tag <- ckanService.getAllTags) {
	  println("Tag: " + tag)
  }
  
  println("Defined datasets:")
  for (dataset <- ckanService.getAllDatasets ) {
    println("Dataset: " + dataset)
  }
  */
  
  var datasetName = "teaching_assistants_in_maintained_schools" //"social_trends" //"yago"
  println("Dataset details: " + datasetName)
  var dataset = ckanService.getDataset(datasetName)
  dataset = ckanService.getDataset(datasetName)
  println("Dataset: id=" + dataset.id + ",name=" + dataset.name + ", version=" + dataset.version + ", author=" + dataset.author) 
  println("Metadata created: " + dataset.metadata_created_date)
  println("tags: " + convertScalaListToJavaList(dataset.tags))
  
  dataset.tags.toArray[String];
  
  
  
  println("groups: " + dataset.groups)
  println("extras: " + dataset.extras)
  for (resource <- dataset.resources) {
    println("Resource: id=" + resource.id + ", url=" + resource.url)
  }
  
  /*
  var datasets = ckanService.searchDatasetsByTags(List("social","communities"))
  println("Found " + datasets.size + " matchings datasets")
  for ( dataset <- datasets ) {
    println("Dataset name: " + dataset.name)
  }
  */
  
  var datasetIds = ckanService.searchDatasetIdsByTag("social")
  println("Dataset IDs: " + datasetIds)
  
}