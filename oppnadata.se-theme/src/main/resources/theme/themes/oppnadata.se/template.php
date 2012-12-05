<?php

function rapidresponse_preprocess_page(&$vars)
{
    $vars['head'] = str_replace('<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />' . "\n", '', $vars['head']);
    $vars['logo_mobile'] = "/sites/default/files/portalen_logo.png";

}


function rapidresponse_preprocess_node(&$vars) {

    $vars['nodeImages'] = array(
        'blog' => 'Comments.png',
        'ckan_dataset' => 'File_PowerPoint.png',
        'webform' => 'Mail_compose.png',
        'page' => 'News.png'
    );


}


function rapidresponse_preprocess_search_block_form(&$vars, $hook)
{
    unset($vars['form']['search_block_form']['#title']);

    unset($vars['form']['search_block_form']['#value']);
    
    // Add a custom class to the search box
    // Set yourtheme.css > #search-block-form .form-text { color: #888888; }
    $vars['form']['search_block_form']['#attributes'] = array(
        'placeholder' => t('Sök..')
    );

    $vars['form']['submit']['#value'] = t('Sök');

    // Rebuild the rendered version (search form only, rest remains unchanged)
    unset($vars['form']['search_block_form']['#printed']);
    $vars['search']['search_block_form'] = drupal_render($vars['form']['search_block_form']);

    // Rebuild the rendered version (submit button, rest remains unchanged)
    unset($vars['form']['submit']['#printed']);
    $vars['search']['submit'] = drupal_render($vars['form']['submit']);
    
    // Collect all form elements to print entire form
    $vars['search_form'] = implode($vars['search']);
}
