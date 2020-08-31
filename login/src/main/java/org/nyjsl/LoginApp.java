package org.nyjsl;

import org.nyjsl.jetpack.mvvm.base.BaseApplication;
import org.nyjsl.network.retrofit.RetrofitConfig;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 4:08 PM
 */
public class LoginApp extends BaseApplication {
    @NonNull
    @Override
    protected RetrofitConfig getRetrofConfig() {
        //TODO
        return new RetrofitConfig();
    }
}
