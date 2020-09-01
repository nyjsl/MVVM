package org.nyjsl.jetpack.mvvm.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
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
        initARouter();
        initLogger(isDebug());
    }

    private void initARouter() {
        if (isDebug()) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);

    }

    protected void initRetrofitManager(){
        RetrofitManager.getInstance().init(getRetrofConfig());
    }

    private void initLogger(boolean debug){
        if (debug) {

        }
    }

    protected abstract boolean isDebug();

    protected abstract @NonNull RetrofitConfig getRetrofConfig();
}
