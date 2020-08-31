package org.nyjsl.network.interceptors;

import android.text.TextUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : weixing
 * @date : 2019-11-07 13:47
 */
public abstract class AuthInterceptor implements Interceptor {


    public AuthInterceptor() {

    }

    protected abstract HashMap<String, String> getAuthHeaderMap();
    protected abstract void updateAuthTokenToLocal(HashMap<String, String> authHeaderMap);


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        HashMap<String, String> authHeaderMap = addAuthTokenToRequestHeader(builder);
        Response response = getAndUpdateAuthTokenFromResponseHeader(chain, request, builder, authHeaderMap);
        return  response;
    }

    /**
     * 从 response header 中读取并更新token (如果有的话)
     * @param chain
     * @param request
     * @param builder
     * @param authHeaderMap
     * @return
     * @throws IOException
     */
    private Response getAndUpdateAuthTokenFromResponseHeader(Chain chain, Request request, Request.Builder builder, HashMap<String, String> authHeaderMap) throws IOException {
        Response response = chain.proceed(builder.method(request.method(), request.body())
                .build());
        if(null != authHeaderMap){
            //是否需要更新token
            boolean shouldUpdate = false;
            for(Map.Entry<String, String> entry: authHeaderMap.entrySet()){
                String header = response.header(entry.getKey());
                if (!TextUtils.isEmpty(header)) {
                    String value = entry.getValue();
                    if (TextUtils.isEmpty(value) || !value.equals(header)) {
                        shouldUpdate = true;
                    }
                    authHeaderMap.put(entry.getKey(),header);
                }
            }
            if(shouldUpdate){
                updateAuthTokenToLocal(authHeaderMap);
            }
        }
        return response;
    }


    /**
     * 请求header中添加token
     * @param builder
     * @return
     */
    private HashMap<String, String> addAuthTokenToRequestHeader(Request.Builder builder) {
        HashMap<String, String> authHeaderMap = getAuthHeaderMap();
        if (null != authHeaderMap) {
            for(Map.Entry<String, String> entry: authHeaderMap.entrySet()){
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    builder.addHeader(entry.getKey(), value);
                }
            }
        }
        return authHeaderMap;
    }
}
