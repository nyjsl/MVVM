package org.nyjsl;

import android.content.ContentValues;
import android.os.Build;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;

import org.nyjsl.jetpack.mvvm.base.BaseApplication;
import org.nyjsl.login.BuildConfig;
import org.nyjsl.network.interceptors.BasicParamsIntercptor;
import org.nyjsl.network.interceptors.JsonContentTypeInterceptor;
import org.nyjsl.network.retrofit.RetrofitConfig;
import org.nyjsl.theme.ParamsKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;

/**
 * @author : weixing
 * @date : 2020/8/31 4:08 PM
 */
public class LoginApp extends BaseApplication {
    @NonNull
    @Override
    protected RetrofitConfig getRetrofConfig() {
        return new RetrofitConfig(interceptors(),null, BuildConfig.DEBUG);
    }


    private List<Interceptor> interceptors() {
        List<Interceptor> result = new ArrayList<>();
        result.add(new JsonContentTypeInterceptor());
        result.add(new LoginAppParamInterceptor());
        result.add(new LoginAuthInterceptor());
        return result;
    }
}
