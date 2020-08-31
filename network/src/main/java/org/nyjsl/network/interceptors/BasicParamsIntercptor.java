package org.nyjsl.network.interceptors;

import android.text.TextUtils;

import com.blankj.utilcode.util.ObjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author : weixing
 * @date : 2019-11-07 14:51
 */
public abstract class BasicParamsIntercptor implements Interceptor {

    private static final String GET = "GET";

    private final HashMap<String, String> paramsMap;

    public BasicParamsIntercptor() {
        this.paramsMap = getParamsMap();
    }

    protected abstract @NonNull HashMap<String, String> getParamsMap();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(ObjectUtils.isNotEmpty(paramsMap)){
            if(GET.equals(request.method())){
                //get 先不拼接基本参数
                request = handleGetUrlParams(request);
            }else{
                request = handleJsonBody(request);
            }
        }
        return chain.proceed(request);
    }

    private Request handleJsonBody(Request request) {
        String oldBody = bodyToString(request.body());
        JSONObject jsonObject;
        try {
            if (!TextUtils.isEmpty(oldBody)) {
                jsonObject = new JSONObject(oldBody);
            }else{
                jsonObject = new JSONObject();
            }
            final Set<Map.Entry<String, String>> entries = paramsMap.entrySet();
            for(Map.Entry<String, String> e:entries){
                if(!jsonObject.has(e.getKey())){
                    jsonObject.put(e.getKey(),e.getValue());
                }
            }
            MediaType contentType = request.body().contentType();
            RequestBody body = RequestBody.create(contentType, jsonObject.toString());
            request = request.newBuilder().method(request.method(),body).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request;
    }

    private String bodyToString(final RequestBody request) {
        try(final Buffer buffer = new Buffer()) {
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "erro while write reqest body to string";
        }
    }

    /**
     * get请求url后面增加通用参数
     * @param request
     * @return
     */
    private Request handleGetUrlParams(Request request) {
        HttpUrl url = request.url();
        final Set<String> names = url.queryParameterNames();
        if(null != names){
            final HttpUrl.Builder builder = url.newBuilder();
            final Set<Map.Entry<String, String>> entries = paramsMap.entrySet();
            for(Map.Entry<String, String> e:entries){
                if(!names.contains(e.getKey())){
                    builder.addQueryParameter(e.getKey(),e.getValue());
                }
            }
            HttpUrl modifiedUrl =  builder.build();
            request =  request.newBuilder().url(modifiedUrl).build();
        }
        return request;
    }
}
