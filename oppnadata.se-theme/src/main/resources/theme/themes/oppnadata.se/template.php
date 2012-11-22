<?php

function rapidresponse_preprocess_page(&$vars)
{
    $vars['head'] = str_replace('<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />' . "\n", '', $vars['head']);
    $vars['logo_mobile'] = "/sites/default/files/portalen_logo.png";
}
