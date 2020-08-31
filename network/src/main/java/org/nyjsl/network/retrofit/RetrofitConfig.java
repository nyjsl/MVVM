package org.nyjsl.network.retrofit;

import java.util.List;

import okhttp3.Interceptor;

/**
 * @author : weixing
 * @date : 2020/8/31 4:13 PM
 */
public class RetrofitConfig {

    private List<Interceptor> interceptors = null;
    private List<Interceptor> networkInterceptors = null;
    private boolean debug = false;

    public RetrofitConfig() {

    }

    public RetrofitConfig(List<Interceptor> interceptors, List<Interceptor> networkInterceptors, boolean debug) {
        this.interceptors = interceptors;
        this.networkInterceptors = networkInterceptors;
        this.debug = debug;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public List<Interceptor> getNetworkInterceptors() {
        return networkInterceptors;
    }

    public void setNetworkInterceptors(List<Interceptor> networkInterceptors) {
        this.networkInterceptors = networkInterceptors;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
