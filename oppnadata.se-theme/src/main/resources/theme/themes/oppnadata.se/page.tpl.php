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

        <div id="main">
            <header>
                
                <a href="/"><img class="logo" src="/sites/default/files/wireframe.png" /></a>
              
                <h1><a href="/">&Ouml;ppnadata</a></h1>

                <div id="header-right">
                    <?php print $header_right; ?>
                </div>

                <div id="nav-container">
                    <nav id="nav">
                        <?= $nav; ?>
                    </nav>
                </div>

            </header>

        <section>
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

            <div id="floatfix">
                &nbsp;
            </div>

        </section>

        <footer>

            <?= $footer_message; ?>

            <?php if (!empty($footer)): print $footer; endif; ?>

            <div class="info">
                <h3>Denna webbplats</h3>            
                <p>Webbplatesen ing&aring;r en satsning f&ouml;r sveriges framtid.&nbsp;<a href="/node/7">Om webbplatsen</a></p>
            </div>

            <div class="contact">
                <h3>Kontakt</h3>        
                <ul>
                    <li><a href="mailto:info@webbriktlinjer.se" class="icon icon-mail">info@webbriktlinjer.se</a></li>
                    <li>Ring oss p&aring; +46 (0)70 - 555 55 55</li>
                </ul>
            </div>   
            <div class="syndication">
                <h3>F&ouml;lj oss</h3>
                <ul>
                    <li><a class="icon-fb" href="http://www.facebook.com/groups/oppendata/"><img src="/sites/default/files/icons/Facebook.png" />Facebook</a></li>
                    <li><a class="icon-twitter" href="https://twitter.com/#!/oppendata"><img src="/sites/default/files/icons/Twitter.png" />Twitter</a></li>
                    <li><a class="icon-rss" href="/nyheter/feed/"><img src="/sites/default/files/icons/RSS.png" />RSS</a></li>
                </ul>
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
