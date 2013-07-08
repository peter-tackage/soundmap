package com.moac.android.soundmap.api;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;

public class GsonRequest<T> extends Request<T> {

    private final Gson mGson;
    private final Response.Listener<T> mOkListener;
    private final TypeToken<T> mType;

    public GsonRequest(Gson gson, String url, TypeToken<T> type, Response.Listener<T> okListener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mGson = gson;
        mType = type;
        mOkListener = okListener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            // TODO Handle response code and return Response.error
            String json = new String(
              response.data, HttpHeaderParser.parseCharset(response.headers));
            T model = mGson.fromJson(json, mType.getType());
            return Response.success(
              model, HttpHeaderParser.parseCacheHeaders(response));
        } catch(UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch(JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        mOkListener.onResponse(t);
    }
}
