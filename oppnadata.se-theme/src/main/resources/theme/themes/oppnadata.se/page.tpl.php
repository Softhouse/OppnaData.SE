<!DOCTYPE html>
<html lang="<?php print $language->language ?>" dir="<?php print $language->dir ?>">
    <head>
        <?php print $head; ?>
        <title><?php print $head_title; ?></title>
        <?php print $styles; ?>
        <?php print $scripts; ?>
        <script type="text/javascript"><?php /* Needed to avoid Flash of Unstyled Content in IE */ ?> </script>
    </head>


    <body class="<?php print $body_classes; ?>">
        <header>
            <div id="logo">
                <?php if (!empty($logo)): ?>

                    <a href="<?= $front_page; ?>" title="<?= t('Home'); ?>" rel="home">
                        <img src="<?= $logo; ?>" alt="<?= t('Home'); ?>" />
                    </a>
                <?php endif; ?>
            </div>

            <div id="logo-mobile">
                <img src="/sites/default/files/portalen_logo.png" alt="Home" />
            </div>

            <div id="header-right">

                <?php if (!empty($search_box)): ?>

                    <div id="search-box">
                        <?= $search_box; ?>

                    </div>
                <?php endif; ?>

            </div>

            <div id="name-and-slogan">
                <?php if (!empty($site_name)): ?>

                    <h1><a href="<?= $front_page; ?>" title="<?= t('Home'); ?>" rel="home"><?= $site_name; ?></a></h1>
                <?php endif; ?>

                <?php if (!empty($site_slogan)): ?>

                    <h2><?= $site_slogan; ?></h2>
                <?php endif; ?>

            </div> <!-- /name-and-slogan -->


            <nav class="<?php if (!empty($primary_links)) { print "withprimary"; } if (!empty($secondary_links)) { print " withsecondary"; } ?> ">
                <?php if (!empty($primary_links)): ?>

                    <div id="primary" class="clear-block">
                      <?= theme('links', $primary_links, array('class' => 'links primary-links')); ?>

                    </div>
                <?php endif; ?>

                <?php if (!empty($secondary_links)): ?>

                    <div id="secondary" class="clear-block">
                        <?= theme('links', $secondary_links, array('class' => 'links secondary-links')); ?>

                    </div>
                <?php endif; ?>

            </nav>

            <?php if (!empty($header)): ?>

                <div id="header-region">
                    <?= $header; ?>

                </div>
            <?php endif; ?>

        </header>




        <?php if (!empty($left)): ?>
            <aside id="sidebar-left" class="column sidebar">

                <?= $left; ?>

            </aside>
        <?php endif; ?>




        <div id="main">
            <?php if (!empty($breadcrumb)): ?><div id="breadcrumb"><?= $breadcrumb; ?></div><?php endif; ?>

            <?php if (!empty($mission)): ?><div id="mission"><?= $mission; ?></div><?php endif; ?>

            <section>
                <?php if (!empty($title)): ?><h1 class="title" id="page-title"><?= $title; ?></h1><?php endif; ?>

                <?php if (!empty($tabs)): ?><div class="tabs"><?= $tabs; ?></div><?php endif; ?>

                <?php if (!empty($messages)): print $messages; endif; ?>

                <?php if (!empty($help)): print $help; endif; ?>

                <div id="content-content" class="clear-block">
                    <?= $content; ?>

                </div> <!-- /content-content -->
                <?= $feed_icons; ?>

            </section>
        </div>



        <?php if (!empty($right)): ?>

            <aside id="sidebar-right" class="column sidebar">

                <?= $right; ?>

            </aside> <!-- /sidebar-right -->
        <?php endif; ?>




        <footer>
            <?= $footer_message; ?>

            <?php if (!empty($footer)): print $footer; endif; ?>

        </footer>

        <?= $closure; ?>

    </body>
</html>
