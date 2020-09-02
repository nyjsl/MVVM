package org.nyjsl;


import org.nyjsl.common.api.BaseUrls;
import org.nyjsl.jetpack.mvvm.base.BaseApplication;
import org.nyjsl.login.BuildConfig;
import org.nyjsl.network.retrofit.RetrofitConfig;
import org.nyjsl.common.api.config.CommonRetrofitConfig;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 4:08 PM
 */
public class LoginApp extends BaseApplication {

    @Override
    protected void initBaseApi() {
        BaseUrls.init(BuildConfig.class);
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @NonNull
    @Override
    protected RetrofitConfig getRetrofConfig() {
        return CommonRetrofitConfig.getInstance(isDebug());
    }


}
