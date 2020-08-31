package org.nyjsl.network.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : weixing
 * @date : 2019-11-07 11:23
 */
public abstract class ContentTypeInterceptor implements Interceptor {

    private static final String CONTENT_TYPE = "Content-type";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder()
                .addHeader(CONTENT_TYPE, getMimeType())
                .method(request.method(), request.body())
                .build());
    }

    protected abstract String getMimeType();

}
