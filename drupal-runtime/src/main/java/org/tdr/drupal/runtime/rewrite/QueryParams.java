package org.tdr.drupal.runtime.rewrite;

import java.util.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class QueryParams {
	
	private static class KVP {
	    final String key;
	    final String value;
	    KVP (String key, String value) {
	        this.key = key;
	        this.value = value;
	    }
	}
	
	List<KVP> query = new ArrayList<KVP>();
	
	public QueryParams(String queryString) {
	    parse(queryString);
	}
	
	public QueryParams() {
	}
	
	public void addParam(String key, String value) {
	    if (key == null || value == null)
	        throw new NullPointerException("null parameter key or value");
	    query.add(new KVP(key, value));
	}
	
	public void removeParam(String key) {
		List<KVP> paramsToRemove = new ArrayList<KVP>();
		for (KVP kvp : query) {
	        if (kvp.key.equals(key)) {
	            paramsToRemove.add(kvp);
	        }
	    }
		for ( KVP kvp : paramsToRemove ) {
			query.remove(kvp);
		}
	}
	
	public int size() {
		return query.size();
	}
	
	private void parse(String queryString) {
	    for (String pair : queryString.split("&")) {
	        int eq = pair.indexOf("=");
	        if (eq < 0) {
	            // key with no value
	            addParam(URLDecoder.decode(pair), "");
	        } else {
	            // key=value
	            String key = URLDecoder.decode(pair.substring(0, eq));
	            String value = URLDecoder.decode(pair.substring(eq + 1));
	            query.add(new KVP(key, value));
	        }
	    }
	}
	
	public String toQueryString() {
	    StringBuilder sb = new StringBuilder();
	    for (KVP kvp : query) {
	        if (sb.length() > 0) {
	            sb.append('&');
	        }
	        sb.append(URLEncoder.encode(kvp.key));
	        if (!kvp.value.equals("")) {
	            sb.append('=');
	            sb.append(URLEncoder.encode(kvp.value));
	        }
	    }
	    return sb.toString();
	}
	
	public String getParameter(String key) {
	    for (KVP kvp : query) {
	        if (kvp.key.equals(key)) {
	            return kvp.value;
	        }
	    }
	    return null;
	}
	
	public List<String> getParameterValues(String key) {
	    List<String> list = new LinkedList<String>();
	    for (KVP kvp : query) {
	        if (kvp.key.equals(key)) {
	            list.add(kvp.value);
	        }
	    }
	    return list;
	}

	public static void main(String[] args) {
	    QueryParams qp = new QueryParams("k1=v1&k2&k3=v3&k1=v4&k1&k5=hello+%22world");
	    System.out.println("getParameter:");
	    String[] keys = new String[] { "k1", "k2", "k3", "k5" };
	    for (String key : keys) {
	        System.out.println(key + ": " + qp.getParameter(key));
	    }
	    System.out.println("getParameters(k1): " + qp.getParameterValues("k1"));
	}
}