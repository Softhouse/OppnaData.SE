<?php
?>
  <div class="node<?php if ($sticky) { print " sticky"; } ?><?php if (!$status) { print " node-unpublished"; } ?>">
    
    <?php if ($page == 0) { ?>
    <header>
            <!--
            <div class="icon-container">
                <img src="/sites/default/files/icons/<?= $nodeImages[$node->type]; ?>" alt="<?= $node->type ?>" />
            </div>
            -->
            <h2><a href="<?php print $node_url?>"><?php print $title?></a></h2>
        <!-- <span class="submitted"><?php print $submitted?></span> -->
        <div class="taxonomy"><?php print $terms?></div>
    </header>
    <?php }; ?>

    <section>
        <div class="content"><?php print $content?></div>
    </section>

    <?php if ($links) { ?><footer class="links">&raquo; <?php print $links?></footer><?php }; ?>
  </div>
