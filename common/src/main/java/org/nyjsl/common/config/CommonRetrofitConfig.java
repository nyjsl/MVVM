package org.nyjsl.common.config;

import org.nyjsl.network.interceptors.JsonContentTypeInterceptor;
import org.nyjsl.network.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * @author : weixing
 * @date : 2020/9/1 2:40 PM
 */
public class CommonRetrofitConfig extends RetrofitConfig {


    private static volatile CommonRetrofitConfig INSTANCE = null;

    private CommonRetrofitConfig(List<Interceptor> interceptors, List<Interceptor> networkInterceptors, boolean debug) {
        super(interceptors,networkInterceptors,debug);
    }

    public static CommonRetrofitConfig getInstance(boolean debug) {
        if (null == INSTANCE) {
            synchronized (CommonRetrofitConfig.class) {
                if (null == INSTANCE) {
                    INSTANCE = new CommonRetrofitConfig(interceptors(), null, debug);
                }
            }
        }
        return INSTANCE;
    }

    private static List<Interceptor> interceptors() {
        List<Interceptor> result = new ArrayList<>();
        result.add(new JsonContentTypeInterceptor());
        result.add(new CommonParamInterceptor());
        result.add(new CommonAuthInterceptor());
        return result;
    }
}
