<?php
?>
  <div class="node<?php if ($sticky) { print " sticky"; } ?><?php if (!$status) { print " node-unpublished"; } ?>">
    <header>
        <?php if ($page == 0) { ?>
            <div class="icon-container">
                <img src="/sites/default/files/icons/<?= $nodeImages[$node->type]; ?>" alt="<?= $node->type ?>" />
            </div>
            <h2><a href="<?php print $node_url?>"><?php print $title?></a></h2>
        <?php }; ?>
        <!-- <span class="submitted"><?php print $submitted?></span> -->
        <div class="taxonomy"><?php print $terms?></div>
    </header>

    <div class="content"><?php print $content?></div>

    <?php if ($links) { ?><footer class="links">&raquo; <?php print $links?></footer><?php }; ?>
  </div>
