package org.nyjsl.jetpack.mvvm.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import org.nyjsl.network.retrofit.RetrofitConfig;
import org.nyjsl.network.retrofit.RetrofitManager;

import androidx.annotation.NonNull;


/**
 * @author : weixing
 * @date : 2020/8/31 4:09 PM
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initRetrofitManager();
    }

    protected void initRetrofitManager(){
        RetrofitManager.getInstance().init(getRetrofConfig());
    }

    protected abstract @NonNull RetrofitConfig getRetrofConfig();
}
