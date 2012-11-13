<?php
// $Id$

/**
 * @file
 */
 
/**
 * Implements hook_field_setttings_form()
 * Adding 3 new fields to the form
 */
function ckan_field_field_settings_form($field) {
  $form = array();

  $form['filter_label'] = array(
    '#type'   =>  'textfield',
    '#title'  =>  t('Label'),
    '#default_value' => $field['filter_label'],
    '#description'  =>  t('The value for the label of the Filter box on the dataset page.'),
  );
  
  $form['filter_description'] = array(
    '#type'   =>  'textarea',
    '#title'  =>  t('Description'),
    '#default_value' => $field['filter_description'],
    '#description'  =>  t('The description which appears under/next to the Filter box on the dataset page. This is usually the OGDI filter documentation.'),
    '#rows'   => 20,
  );
  
  // Load in default helptext
  $filter_helptext = file_get_contents(drupal_get_path('module', 'ckan_field') . '/ckan_field_helptext.txt');
  
  $form['filter_helptext'] = array(
    '#type'   =>  'textarea',
    '#title'  =>  t('Help Text'),
    '#default_value' => $field['filter_helptext'] ? $field['filter_helptext'] : $filter_helptext,
    '#description'  =>  t('The help text that will appear enclosed in a tooltip to the right of the filter textarea. To reset this to the default text, please delete the contents of this textarea, save the field settings, come back to this page and re-save the field settings again.'),
    '#rows'   => 20,
  );
  
  return $form;
}


/**
 * Implements hook_field_settings($op = 'save').
 */
function ckan_field_field_settings_save($field) {
  return array('filter_label', 'filter_description', 'filter_helptext');
}


/**
 * Implements hook_field_settings($op = 'database_columns').
 */
function ckan_field_field_settings_database_columns($field) {
  return array(
    'value' => array('type' => 'varchar', 'length' => 255, 'not null' => FALSE, 'sortable' => TRUE, 'views' => TRUE),
  );
}


/**
 * Implements hook_field_settings($op = 'views_data').
 */
function ckan_field_field_settings_views_data($field) {
  // Start with the $data created by CCK
  // and alter it as needed. The following
  // code illustrates how you would retrieve
  // the necessary data.
  $data = content_views_field_views_data($field);
  $db_info = content_database_info($field);
  $table_alias = content_views_tablename($field);
  $field_data = $data[$table_alias][$field['field_name'] .'_value'];
  // Make changes to $data as needed here.
  return $data;
}