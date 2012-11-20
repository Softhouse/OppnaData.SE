<?php
  echo 'HELLO!!!!<br/>';
  $ckan = service_ref('ckanService');
  $dataset = $ckan->getDataset('teaching_assistants_in_maintained_schools');
  echo $dataset->id(); 
?>
