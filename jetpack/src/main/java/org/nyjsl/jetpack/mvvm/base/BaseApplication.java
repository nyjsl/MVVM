package org.nyjsl.jetpack.mvvm.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.tencent.mmkv.MMKV;

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
        MMKV.initialize(this);
        initRetrofitManager();
        initVMRouter();
    }

    private void initVMRouter() {
        // 创建RootHandler
        DefaultRootUriHandler rootHandler = new DefaultRootUriHandler(this);
        // 初始化
        Router.init(rootHandler);
    }

    protected void initRetrofitManager(){
        RetrofitManager.getInstance().init(getRetrofConfig());
    }

    protected abstract @NonNull RetrofitConfig getRetrofConfig();
}
