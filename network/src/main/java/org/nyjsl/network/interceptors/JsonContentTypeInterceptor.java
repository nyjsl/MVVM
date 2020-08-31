package org.nyjsl.network.interceptors;

/**
 * @author : weixing
 * @date : 2020/8/31 4:26 PM
 */
public final class JsonContentTypeInterceptor extends ContentTypeInterceptor{


    private static final String MIME_TYPE_JSON = "application/json;charset=UTF-8";
    @Override
    protected String getMimeType() {
        return MIME_TYPE_JSON;
    }

}
