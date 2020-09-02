package org.nyjsl.jetpack.mvvm.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.core.Debugger;
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
        initLogger(isDebug());
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

    private void initLogger(boolean debug){
        if (debug) {
            setWMLogger();
        }
    }

    /**
     * WMRouter 的Log
     */
    private void setWMLogger() {
        // 自定义Logger
        DefaultLogger logger = new DefaultLogger() {
            @Override
            protected void handleError(Throwable t) {
                super.handleError(t);
            }
        };
        // 设置Logger
        Debugger.setLogger(logger);
        // Log开关，建议测试环境下开启，方便排查问题。
        Debugger.setEnableLog(true);

        // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
        Debugger.setEnableDebug(true);
    }

    protected abstract boolean isDebug();

    protected abstract @NonNull RetrofitConfig getRetrofConfig();
}
