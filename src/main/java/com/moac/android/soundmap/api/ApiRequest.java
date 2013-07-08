package com.moac.android.soundmap.api;

import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest<T> {

    private static final String UTF_8 = "UTF-8";
    private static final String FORMAT = ".json";

    private final TypeToken<T> mTargetType;
    private final String mEndpoint;
    private Map<String, String> mQueries = new HashMap<String, String>();

    public ApiRequest(TypeToken<T> type, String _endpoint) {
        mTargetType = type;
        mEndpoint = _endpoint;
    }

    public ApiRequest withQuery(String _param, String _value) {
        mQueries.put(_param, _value);
        return this;
    }

    public ApiRequest withQuery(String _param, int _value) {
        mQueries.put(_param, String.valueOf(_value));
        return this;
    }

    public String getEndpoint() { return mEndpoint; }
    public Map<String, String> getQueries() { return mQueries; }
    public TypeToken<T> getTargetType() { return mTargetType; }

    public String toUrl() {
        return toUrl(UTF_8);
    }

    public String toUrl(String _encoding) {
        if(mQueries == null || mQueries.isEmpty())
            return null;

        StringBuilder sb = new StringBuilder(mEndpoint);
        sb.append(FORMAT);
        sb.append("?");
        for(Map.Entry<String, String> param : mQueries.entrySet()) {
            sb.append(toQueryEntry(param.getKey(), param.getValue(), _encoding));
        }
        return sb.toString().substring(0, sb.length() - 1); // remove trailing &
    }

    private static String toQueryEntry(String _key, String _value, String _encoding) {
        StringBuilder sb = new StringBuilder();
        try {
            String query = String.format("%s=%s",
              URLEncoder.encode(_key, _encoding),
              URLEncoder.encode(_value, _encoding));
            sb.append(query);
            sb.append("&");
            return sb.toString();
        } catch(UnsupportedEncodingException ignored) {
        }
        return sb.toString();
    }
}
