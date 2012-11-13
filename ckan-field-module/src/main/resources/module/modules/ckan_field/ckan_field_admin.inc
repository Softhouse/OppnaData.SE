<?php
// $Id$

/**
 * @file
 */

/**
 * FormAPI callback funciton to build admin settings screen
 */
function ckan_field_admin() {
  $form = array();

  $form['ckan_field_label'] = array(
    '#type'   =>  'textfield',
    '#title'  =>  t('Label'),
    '#default_value' => variable_get('ckan_field_label', t('Filter expression ($filter)')),
    '#description'  =>  t('The value for the label of the Filter box on the dataset page.'),
  );
  
  $form['ckan_field_description'] = array(
    '#type'   =>  'textarea',
    '#title'  =>  t('Description'),
    '#default_value' => variable_get('ckan_field_description', ''),
    '#description'  =>  t('The description which appears under/next to the Filter box on the dataset page. This is usually the OGDI filter documentation.'),
    '#rows'   => 20,
  );
  
  $form['ckan_field_helptext'] = array(
    '#type'   =>  'textarea',
    '#title'  =>  t('Help Text'),
    '#default_value' => variable_get('ckan_field_helptext', ''),
    '#description'  =>  t('The help text that will appear enclosed in a tooltip to the right of the filter textarea.'),
    '#rows'   => 20,
  );
  
  return system_settings_form($form);
}