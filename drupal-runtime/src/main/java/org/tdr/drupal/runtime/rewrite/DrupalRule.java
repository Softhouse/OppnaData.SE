package org.tdr.drupal.runtime.rewrite;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tuckey.web.filters.urlrewrite.extend.RewriteMatch;
import org.tuckey.web.filters.urlrewrite.extend.RewriteRule;
import org.tuckey.web.filters.urlrewrite.utils.Log;

public class DrupalRule extends RewriteRule {
	
    private static Log log = Log.getLog(DrupalRule.class);

    private ServletContext sc;
    
    public boolean init(ServletContext sc) {
        this.sc = sc;
        return true;
    }

    /* (non-Javadoc)
     * @see org.tuckey.web.filters.urlrewrite.extend.RewriteRule#matches(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public RewriteMatch matches(HttpServletRequest request, HttpServletResponse response)
    {
        String requestURI = request.getRequestURI();
        if ( requestURI == null ) {
            return null;
        }
        
        if ( requestURI.equals(request.getContextPath()) ) {
            return null;
        }
        
        requestURI = requestURI.replaceAll(request.getContextPath(), "");
        log.debug("@DRUPALRULE: RequestUri = "+requestURI);
        
        if ( requestURI.startsWith("/misc") || 
        	 requestURI.startsWith("/modules") ||
        	 requestURI.startsWith("/themes") ||
        	 requestURI.startsWith(("/sites")) ) {
            return null;
        }
        
        String realPath = sc.getRealPath(requestURI);
        log.debug("@DRUPALRULE: File check real path = " + realPath);
        if ( realPath != null) { // resource exists -> skip resolving to Drupal page URI
                return null;
        }

        log.debug("@DRUPALRULE: DrupalMatch for requestURI = "+requestURI);
        return new DrupalMatch();
    }
}
