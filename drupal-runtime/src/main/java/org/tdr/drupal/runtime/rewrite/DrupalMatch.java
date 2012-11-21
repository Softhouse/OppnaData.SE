
package org.tdr.drupal.runtime.rewrite;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tuckey.web.filters.urlrewrite.extend.RewriteMatch;
import org.tuckey.web.filters.urlrewrite.utils.Log;

public class DrupalMatch extends RewriteMatch {
	
    private static Log log = Log.getLog(DrupalMatch.class);

    /* (non-Javadoc)
     * @see org.tuckey.web.filters.urlrewrite.extend.RewriteMatch#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String queryString = request.getQueryString();
        
        String contextstr = request.getContextPath();
        String requestUri = request.getRequestURI();
        String drupalpath = requestUri.replaceAll(contextstr, "").substring(1);
        StringBuilder newURI = new StringBuilder(512);
        
        newURI.append("/index.php?q=").append(drupalpath);
        
//        if( queryString != null ) {
//            newURI.append("&").append(request.getQueryString());
//        }
        
        if ( queryString != null ) {
        	QueryParams queryParams = new QueryParams(queryString);
        	queryParams.removeParam("q"); // Remove eventual drupal path attribute
        	if ( queryParams.size() > 0 ) {
        		newURI.append("&").append(queryParams.toQueryString());
        	}
        }
        
        log.debug("New URI = "+newURI.toString());
        RequestDispatcher rd = request.getRequestDispatcher(newURI.toString());
        rd.forward(request, response);
        return true;
    }
}
