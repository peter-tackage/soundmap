package com.moac.android.soundmap.api;

import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest<T> {

    private static final String UTF_8 = "UTF-8";

    private final TypeToken<T> targetType;
    private final String endpoint;
    private Map<String, String> queryMap = new HashMap<String, String>();

    public ApiRequest(TypeToken<T> type, String endpoint) {
        targetType = type;
        this.endpoint = endpoint;
    }

    public ApiRequest withQuery(String param, String value) {
        queryMap.put(param, value);
        return this;
    }

    public ApiRequest withQuery(String param, int value) {
        queryMap.put(param, String.valueOf(value));
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public TypeToken<T> getTargetType() {
        return targetType;
    }

    public String toUrl() {
        return toUrl(UTF_8);
    }

    public String toUrl(String encoding) {
        if (queryMap.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder(endpoint);
        sb.append("?");
        for (Map.Entry<String, String> param : queryMap.entrySet()) {
            sb.append(toQueryEntry(param.getKey(), param.getValue(), encoding));
        }
        return sb.toString().substring(0, sb.length() - 1); // remove trailing &
    }

    private static String toQueryEntry(String key, String value, String encoding) {
        StringBuilder sb = new StringBuilder();
        try {
            String query = String.format("%s=%s",
                    URLEncoder.encode(key, encoding),
                    URLEncoder.encode(value, encoding));
            sb.append(query);
            sb.append("&");
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported encoding: " + encoding, e);
        }
    }
}
