package com.asu.MovieRecommender.utility;


import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;


/**
 *  URL Builder
 *
 * @author Lehar Bhatt
 */
public class ApiUrl {

    /*
     * TmdbApi API Base URL
     */

    private static final String APPEND_TO_RESPONSE = "append_to_response";

    private final String baseUrl;
    
    @Value("${movie.pi.key}")
    private String key;
    
    
    private final Map<String, String> params = new HashMap<String, String>();

    public ApiUrl(Object... urlElements) {
        StringBuilder baseUrlBuilder = new StringBuilder(Constants.URL);

        for (int i = 0; i < urlElements.length; i++) {
            baseUrlBuilder.append(urlElements[i]);

            if (i < urlElements.length - 1) {
                baseUrlBuilder.append("/");
            }
        }

        baseUrl = baseUrlBuilder.toString();
    }

    
    public String buildUrlString() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        try {

            if (params.size() > 0) {
                List<String> keys = new ArrayList<String>(params.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    urlBuilder.append(i == 0 ? "?" : "&");
                    String paramName = keys.get(i);

                    urlBuilder.append(paramName).append("=");
                    urlBuilder.append(URLEncoder.encode(params.get(paramName), "UTF-8"));
                }
            }
            return urlBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        
    }
    

    public URL buildUrl() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        try {

            if (params.size() > 0) {
                List<String> keys = new ArrayList<String>(params.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    urlBuilder.append(i == 0 ? "?" : "&");
                    String paramName = keys.get(i);

                    urlBuilder.append(paramName).append("=");
                    urlBuilder.append(URLEncoder.encode(params.get(paramName), "UTF-8"));
                }
            }

            return new URL(urlBuilder.toString());

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    public void addParam(String name, Object value) {
        addParam(name, value.toString());
    }


    public void addParam(String name, String value) {
        if (params.containsKey(name)) {
            throw new RuntimeException("paramater '" + name + "' already defined");
        }

        name = StringUtils.trimToEmpty(name);
        if (name.isEmpty()) {
            throw new RuntimeException("parameter name can not be empty");
        }

        value = StringUtils.trimToEmpty(value);
        if (value.isEmpty()) {
            throw new RuntimeException("value of parameter '" + name + "' can not be empty");
        }

        params.put(name, value);
    }


    /**
     * Add argument
     *
     * @param key
     * @param value
     */
    public void addParam(String key, int value) {
        addParam(key, Integer.toString(value));
    }


    /**
     * Add argument
     *
     * @param key
     * @param value
     */
    public void addParam(String key, boolean value) {
        addParam(key, Boolean.toString(value));
    }


    /**
     * Convenience wrapper around addArgument
     *
     * @param appendToResponse Comma separated, any movie method
     */
    public void appendToResponse(String... appendToResponse) {
        if (appendToResponse == null || appendToResponse.length == 0) {
            return;
        }

        addParam(APPEND_TO_RESPONSE, StringUtils.join(appendToResponse, ","));
    }


    public void addPage(Integer page) {
        if (page != null && page > 0) {
            addParam(Constants.PARAM_PAGE, page);
        }
    }


    public void addLanguage(String language) {
        if (StringUtils.isNotBlank(language)) {
            addParam(Constants.PARAM_LANGUAGE, language);
        }
    }
    
    public void addkey() {
    	System.out.println(key);
    	if (StringUtils.isNotBlank(key)) {
            //addParam(Constants.PARAM_API_KEY, configuration.getKey());
    		addParam(Constants.PARAM_API_KEY, key);
        }
    }
}
