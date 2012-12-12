<?php

function getDatasetsByCkanTag($ckan_tag) {
	$nodeids = array();
	$ckan = service_ref('ckanService');
	$datasets = $ckan->searchDatasetIdsByTag($ckan_tag);
	if ( $datasets->size() > 0 ) {
		$datasetsStr = "'" . $datasets->mkString("','") . "'";
		$result = db_query("SELECT nid FROM content_type_ckan_dataset WHERE  field_ckan_dataset_id_value IN ( $datasetsStr )");
		while ($row = db_fetch_array($result))  {
			$nodeids[] = $row['nid'];
		}
	}
	return $nodeids;
}

function searchNodesByMultipleTaxonomies($taxonomy_cond) {
	
	$nodeids = array();
	$tok = strtok($taxonomy_cond, "-");
	$sql = "SELECT nid from node WHERE ";
	$firstCond = true;
	while ( $tok !== false ) {
		if ( ! $firstCond ) {
			$sql .= " AND ";
		}
		$sql .= "nid IN (SELECT nid from term_node WHERE tid = $tok)";
		if ( $firstCond ) {
			$firstCond = false;
		}
		$tok = strtok("-");
	}
	$result = db_query($sql);
	while ($row = db_fetch_array($result))  {
		$nodeids[] = $row['nid'];
	}
	return $nodeids;
}
