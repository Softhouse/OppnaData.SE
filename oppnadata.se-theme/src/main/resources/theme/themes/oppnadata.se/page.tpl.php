<?php
    // require("block.tpl.php");
?>

<!DOCTYPE html>
<html lang="<?php print $language->language ?>" dir="<?php print $language->dir ?>">
    <head>
        <meta charset="utf-8" />
        <link href='http://fonts.googleapis.com/css?family=Montserrat|Open+Sans:400italic,400,700' rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <?php print $head; ?>
        <title><?php print $head_title; ?></title>
        <?php print $styles; ?>
        <?php print $scripts; ?>
        <script type="text/javascript"><?php /* Needed to avoid Flash of Unstyled Content in IE */ ?> </script>
    </head>


    <body class="<?php print $body_classes; ?>">


        <header>
            <div id="header-container">
                <h1>&Ouml;ppnadata</h1>

                <div id="header-right">
                    <?php print $header_right; ?>
                </div>

                <div id="nav-container">
                    <nav id="nav">
                        <?= $nav; ?>
                    </nav>
                </div>
            </div>
        </header>

        <section>
            <div id="main">
                <?php if (!empty($breadcrumb)): ?><div id="breadcrumbs"><?= $breadcrumb; ?></div><?php endif; ?>

                <?php if (!empty($mission)): ?><div id="mission"><?= $mission; ?></div><?php endif; ?>

                <?php if (!empty($title)): ?><h1 id="page-title"><?= $title; ?></h1><?php endif; ?>

                <?php if (!empty($tabs)): ?><div class="tabs"><?= $tabs; ?></div><?php endif; ?>

                <?php if (!empty($messages)): print $messages; endif; ?>

                <?php if (!empty($help)): print $help; endif; ?>

                <div id="content">

                    <?= $content; ?>

                </div>

                <aside>

                    <?= $right; ?>

                </aside>
            </div>
        </section>

        <footer>
            <div id="footer-container">
                <?= $footer_message; ?>

                <?php if (!empty($footer)): print $footer; endif; ?>

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
            </div>
        </footer>
        <?= $closure; ?>


        <?php
            // print '<pre>';
            // var_dump(get_defined_vars());
            // print '</pre>';
        ?>

    </body>
</html>
