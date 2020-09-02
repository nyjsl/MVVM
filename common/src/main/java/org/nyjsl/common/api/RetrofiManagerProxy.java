package org.nyjsl.common.api;

import org.nyjsl.network.retrofit.RetrofitManager;

/**
 * @author : weixing
 * @date : 2020/9/2 2:17 PM
 */
public class RetrofiManagerProxy {

    private RetrofitManager retrofitManager;

    private RetrofiManagerProxy() {
        this.retrofitManager = RetrofitManager.getInstance();
    }

    private static class RetrofitManagerProxyInstance {
        private final static RetrofiManagerProxy RETROFIT_MANAGER = new RetrofiManagerProxy();
    }

    public static RetrofiManagerProxy getInstance() {
        return RetrofiManagerProxy.RetrofitManagerProxyInstance.RETROFIT_MANAGER;
    }

    /**
     *根据各模块业务接口 获取不同的retrofit service接口对象
     */
    public <T> T getRetrofitService(Class<T> cls,@Host String fieledName) {
        return retrofitManager.getRetrofitService(cls,BaseUrls.getHostByName(fieledName));
    }
}
