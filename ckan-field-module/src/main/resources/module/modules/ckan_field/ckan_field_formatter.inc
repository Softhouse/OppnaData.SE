<?php
// $Id$

/**
 * @file
 */
 
/**
 * Theme function for 'default' ckan_field field formatter.
 * 
 * $element['#item']: the sanitized $delta value for the item,
 * $element['#field_name']: the field name,
 * $element['#type_name']: the $node->type,
 * $element['#formatter']: the $formatter_name,
 * $element['#node']: the $node,
 * $element['#delta']: the delta of this item, like '0',
 * 
 */
function theme_ckan_field_formatter_default($element) {
  $output = '';
  // If there is a dataset to load 
  if (!empty($element['#item']['value'])) {

    /* Add the necessary javascript and css files...*/
    /* Jquery UI */
    drupal_add_css(drupal_get_path('module', 'jquery_ui') ."/jquery.ui/themes/base/ui.all.css", 'module', 'all');
  
    // TODO: SKIP??? OR USE FOR DISPLAYING DATA????
    /* DataTables CSS and JS */
    /*drupal_add_js(drupal_get_path('module', 'ckan_field') .'/plugins/dataTables/media/js/jquery.dataTables.min.js', 'module');
    drupal_add_css(drupal_get_path('module', 'ckan_field') .'/plugins/dataTables/media/css/demo_table.css', 'module', 'all');
    drupal_add_css(drupal_get_path('module', 'ckan_field') .'/plugins/dataTables/media/css/demo_table_jui.css', 'module', 'all');
    */
    
    /* CKAN Stuff */
    drupal_add_js(drupal_get_path('module', 'ckan_field') .'/js/ckan_field.js', 'module');
    drupal_add_css(drupal_get_path('module', 'ckan_field') ."/css/ckan_field.css", 'module', 'all');
  
    /* Reset the sticky table headers - see theme_ckan_field_container() */
    drupal_add_js('$("table.no-sticky thead").addClass("tableHeader-processed");', 'inline', 'footer');

    // Filter out any harmful stuff
    $dataset_id = filter_xss($element['#item']['safe']);
    
    $ckan = service_ref('ckanService');
   
    try {
    	$dataset = $ckan->getDataset($dataset_id);  
    	$output .= render_dataset($dataset);
    }
    catch (Exception $e) {
    	$output .= theme('ckan_field_container', array(), array(), 'Information');
    }
    
  } 
  return $output;
}

function render_dataset($dataset) {
	$output = '';
	
	// Resources table
	//
	$headers = array('Resurs', 'Format');
	
	$rows = array();
	
	foreach ($dataset->resourceArray() as $resource) {
		// TODO: Fallback if no description attached
		$url = '<a href="' . $resource->url() . '">' . $resource->description() . '</a>';
		$rows[] = array($url, $resource->format());
	}
	$output .= theme('ckan_field_container', $headers, $rows, 'Resurser');
	
	// Information table
	//
	$rows = array( array('<b>Titel</b>', $dataset->title()),
			array('<b>Utgivare</b>', $dataset->author()),
			array('<b>Beskrivning</b>', $dataset->notes_rendered()),
			//array('<b>Version</b>', $dataset->version()),
			array('<b>Skapad</b>', $dataset->metadata_created_date()),
			array('<b>Uppdaterad</b>', $dataset->metadata_modified_date()),
			array('<b>Licens</b>', $dataset->license()));
	$extras = $dataset->extrasMap();
	foreach ($extras->keySet() as $extras_key) {
		$extras_value = $extras->get($extras_key);
		if (!empty($extras_value)) {
			$rows[] = array('<b>' . $extras_key . '</b>', $extras_value);
		}
	}
	
	$tags = '';
	foreach ($dataset->tagArray() as $tag) {
		$tags .= '<a href="/ckan_datasets/' . $tag . '">' . $tag . '</a><br/>';
	}
	// $dataset->tags()->mkString('<br/>')
	$rows[] = array('<b>Taggar</b>', $tags); 
	
	

	// TODO: How to convert Scala list to PHP array?? Create som PHP modules for that???
	
	// Theme the container
	$output .= theme('ckan_field_container', array(), $rows, 'Information');
	
	return $output;
}

/**
 * Theme function for 'plain' ckan_field field formatter.
 */
function theme_ckan_field_formatter_plain($element) {
  return strip_tags($element['#item']['safe']);
}

/**
 * Theme function for 'link' ckan_field field formatter.
 */
function theme_ckan_field_formatter_link($element) {
  return l(strip_tags($element['#item']['safe']), strip_tags($element['#item']['safe']));
}


/**
 * Theme function for CKAN data grid and Map in tabs
 */
function theme_ckan_field_container($headers, $rows, $title) {
  // Add the jQuery tabs functionality
  //if (module_exists('jquery_ui')) {
  //  jquery_ui_add('ui.tabs');
  //}
  
  $output .= '<h3>' . $title . '</h3>';

  $output .= '<div id="ckan-wrapper">' . chr(10); 
    //$output .= '<div id="ckan-tabs">'. chr(10);
      // Build the tabs...
      //$output .= '<ul>'. chr(10);
      //$output .= '<li><a href="#ckan-data">Data</a></li>' . chr(10);;
      //$output .= '<li><a href="#ckan-map">Map</a></li>' . chr(10);;
      //$output .= '</ul>'. chr(10);
      // Output the data table...
      $output .= '<div id="ckan-data">' . chr(10);
      $output .= theme('ckan_field_datatable', $headers, $rows);
      $output .= '</div>' . chr(10);
      // Output the map...
      //$output .= '<div id="ckan-map">' . chr(10);
      //$output .= theme('ckan_field_map', $parameters);
      //$output .= '</div>' . chr(10);
    //$output .= '</div>' . chr(10);
  $output .= '</div>' . chr(10);
  return $output;
}


/**
 * Theme function for the CKAN data grid
 */
function theme_ckan_field_datatable($headers, $rows) {
 
  // Output the Path to the ATOM Feed API
  //$output .= theme('ckan_field_atom_link', $datasource, $dataset_id, $querystring);

  // Output the Download select box
  //$output .= theme('ckan_field_download_link', $datasource, $dataset_id, $querystring);
  
  // If no data is returned...
  if (count($rows) == 0) {
    $output = '<div id="ckan-datagrid">' . t('No data could be found.') . '</div>';
  } 
  else {      
    $output .= theme('table', $headers, $rows, $attributes = array('id' => 'ckan-datagrid', 'class' => 'no-sticky'));
  }
  return $output;
}

/**
 * Theme function for the CKAN Filter helptext link and modal window
 */
function theme_ckan_field_helptext($field) {
  $output = '';
  // If there is a value in the field, we want to ouput it..
  if (!empty($field)) {
    if (module_exists('jquery_ui')) {  // If jquery_ui is installed(which it should be)...
      jquery_ui_add('ui.dialog'); // Load the dialog widget...
      $current_page = drupal_get_path_alias($_GET['q']);
      $output .= l('Help on how to write expressions', $current_page, $options = array('attributes' => array('class' => 'ckan-helptip'), 'html' => TRUE));  // Output the help icon...
    }
    $output .= '<div class="ckan-helptext" title="' . t('Expression filter hints') . '"' . chr(10);
    $output .= $field['#value'] . chr(10);
    $output .= '</div>' . chr(10) ;
  }
  return $output;
}


/**
 * Theme function to output the Path to the ATOM Feed API of the visible data
 * TODO: WHAT FEED?
 */
function theme_ckan_field_atom_link($datasource, $dataset_id, $querystring) {
  // Output the Path to the ATOM Feed API
  $url = url($datasource . '/' . $dataset_id, $options = array('html' => TRUE, 'query' => $querystring));
  $output .= '<p>' . urldecode(t('Full query URL: !url', array('!url' => l($url, $url, $options = array('html' => TRUE)))));
  $output .= t(' (Click to view results as XML/Atom)') . '</p>';

  return $output;
}


/**
 * Theme function to output the select field for choosing the type of file format to download the visible data
 */
function theme_ckan_field_download_link($datasource, $dataset_id, $querystring) {
  
  $output .= l('Download', '');
  $form['download']['filetypes'] = array(
    '#type' => 'value',
    '#value' => array(t('CSV'), t('Excel'), t('Daisy'), t('KML')),
  );
  
  $form['download']['choose'] = array(
    '#id' => t('ckan-choosetype'),
    '#type' => 'select',
    '#options' => $form['download']['filetypes']['#value'],
    '#name' => t('ckan-choosetype')
  );
  $output .= drupal_render($form['download']);
  return $output;
}