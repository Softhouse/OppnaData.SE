<?php

function getDatasetsByCkanTag($ckan_tag) {
	$nodeids = array();
	$ckan = service_ref('ckanService');
	$datasets = $ckan->searchDatasetIdsByTag($ckan_tag);
	if ( $datasets->size() > 0 ) {
		$datasetsStr = "'" . $datasets->mkString("','") . "'";
		$result = db_query("SELECT nid FROM content_type_ckan_dataset WHERE  field_dataset_id_value IN ( $datasetsStr )");
		while ($row = db_fetch_array($result))  {
			$nodeids[] = $row['nid'];
		}
	}
	return $nodeids;
}