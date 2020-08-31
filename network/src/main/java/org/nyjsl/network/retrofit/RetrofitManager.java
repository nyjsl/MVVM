package org.nyjsl.network.retrofit;

import android.util.ArrayMap;

import com.blankj.utilcode.util.ObjectUtils;
import org.nyjsl.network.util.LiveDataCallAdapterFactory;

import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * rxjava+retrofit+框架封装
 */

public class RetrofitManager {
    /**
     * 保存一个retrofit的实例，通过（baseUrl来获取）
     */
    private HashMap<String, Retrofit> mRetrofitHashMap = new HashMap<>(8);
    /**
     * 默认的超时时间
     */
    private static final int DEFAULT_MILLISECONDS = 30;

    /**
     * 内部类单列设计模式
     */
    private RetrofitManager() {
    }


    private List<Interceptor> interceptors = null;
    private List<Interceptor> networkInterceptors = null;
    private boolean debug = false;

    /**
     * 初始化RetrofitManager
     */
    public void init(RetrofitConfig retrofitConfig){
        this.interceptors = retrofitConfig.getInterceptors();
        this.networkInterceptors = retrofitConfig.getNetworkInterceptors();
        this.debug = retrofitConfig.isDebug();
    }

    private static class RetrofitManagerInstance {
        private final static RetrofitManager RETROFIT_MANAGER = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerInstance.RETROFIT_MANAGER;
    }

    /**
     * 获取retrofit的实例
     * @return Retrofit
     */
    private Retrofit getRetrofit(String baseUrl) {
        Retrofit retrofit;

        if (mRetrofitHashMap.containsKey(baseUrl)) {
            retrofit = mRetrofitHashMap.get(baseUrl);
        } else {
            retrofit = createrRetrofit(baseUrl);
            mRetrofitHashMap.put(baseUrl, retrofit);
        }

        return retrofit;
    }

    /**
     * 更新地址
     * @param baseurl
     */
    public void update(String baseurl) {
        if (null != mRetrofitHashMap) {
            Retrofit retrofit = createrRetrofit(baseurl);
            mRetrofitHashMap.put(baseurl, retrofit);
        }
    }

    /**
     * 创建retrofit
     *
     * @return Retrofit
     */
    private Retrofit createrRetrofit(String baseurl) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS);
        if (debug) {
            builder.addNetworkInterceptor(
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY));
        }else{
            builder = builder.proxy(Proxy.NO_PROXY);

        }
        if (ObjectUtils.isNotEmpty(interceptors)) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        //第三方依赖的拦截器
        if (ObjectUtils.isNotEmpty(networkInterceptors)) {
            for (Interceptor networkInterceptor : networkInterceptors) {
                builder.addNetworkInterceptor(networkInterceptor);
            }
        }

        OkHttpClient httpClient = builder.build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        return retrofitBuilder
                .baseUrl(baseurl)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(httpClient)
                .build();
    }


    /**
     *根据各模块业务接口 获取不同的retrofit service接口对象
     */
    public <T> T getRetrofitService(Class<T> cls,String baseUrl) {
        return getRetrofitService(baseUrl,cls);
    }
    /**
     *根据各模块业务接口 获取不同的retrofit service接口对象
     */
    private <T> T getRetrofitService(String baseUrl, Class<T> cls) {
        return getRetrofit(baseUrl).create(cls);
    }


}
