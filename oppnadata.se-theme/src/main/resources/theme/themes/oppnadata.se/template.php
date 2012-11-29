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
        'page' => 'News.png'
    );


}
