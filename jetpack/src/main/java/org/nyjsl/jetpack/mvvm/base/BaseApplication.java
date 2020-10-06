package org.nyjsl.jetpack.mvvm.base;

import android.app.Application;

import com.blankj.utilcode.util.ApiUtils;
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
 *
 * JetPack MMVM核心
 *
 * 将容易出错的操作在后台封装好,方便使用者快速,稳定,不产生预期外错误的编码
 *
 * Jetpack LifeCycle
 *
 *    在Lifecycle面世前,生命周期管理,纯靠手工维持,这样容易滋生大量的一致性问题
 *
 *    大量跟声明周期相关的代码分散在不同Activity中,埋下的隐患随指数级增长
 *    代码分散,修改维护都不方便
 *
 *    Lifecycle通过模块方法和观察者模式,将生命周期管理的复杂操作,全部作为LifecycleOwner
 *   的基类中 getLifecycle().addObserver(lifecycleObserver),优雅的完成第三方组件
 *   在自己内部对LifecyclerOwner生命周期的感知
 *
 * Jetpack LiveData
 *
 *     This class is designed to hold individual data fields of {@link ViewModel},
 *     but can also be used for sharing data between different modules in your application
 *     in a decoupled fashion.
 *
 *     LiveData 的存在,主要是为了帮助新手老手 都能不假思索的遵循通过唯一可信源分发状态的标准化开发理念
 *
 *    在LiveData之前我们分发状态都是通过EventBus或者Java Interface来完成
 *
 *    EventBus本身缺乏Lifecycle的加持,存在生命周期管理的一致性问题
 *
 *
 *    public class UserModel extends ViewModel {
 *       private final MutableLiveData<User> userLiveData = new MutableLiveData();
 *
 *       public LiveData<User> getUser() {
 *           return userLiveData;
 *       }
 *
 *       public UserModel() {
 *           // trigger user load.
 *       }
 *
 *       void doAction() {
 *           // depending on the action, do necessary business logic calls and update the
 *           // userLiveData.
 *       }
 *   }
 *
 * Jetpack ViewModel
 *
 *     ViewModel 存在,主要是为了解决状态管理和页通信问题
 *     ViewModel的本职工作是状态托管和状态管理的分治
 *       对于轻量级的状态,可以通过视图控制器基类的savedInstanceState机制,以序列化的方式完全完成恢复和存储
 *       对于重量级的状态,例如网络请求得到的list,可以通过生命周期长于视图控制器的ViewModel持有,
 *       从而直接从ViewModel恢复,而不是序列化
 *
 *     ViewModel is a class that is responsible for preparing and managing the data for
 *     an {@link android.app.Activity Activity} or a {@link androidx.fragment.app.Fragment Fragment}.
 *     It also handles the communication of the Activity / Fragment with the rest of the application
 *     (e.g. calling the business logic classes).
 *     ViewModels can also be used as a communication layer between different Fragments of an Activity.
 *     Each Fragment can acquire the ViewModel using the same key via their Activity. This allows
 *     communication between Fragments in a de-coupled fashion such that they never need to talk to
 *    the other Fragment directly.
 *
 *    ViewModel如何做到这几点?
 *     得益于工厂模式,是的ViewModel被LifecycleOwner持有,通过ViewModelProvider来引用
 *    既类似于单例,实际上又不是单例
 *
 * Jetpack DataBinding
 *
 *    通过在布局中与可观察的数据发生绑定,那么当数据被set新内容时,控件也将得到通知和刷新
 *      无需手工调用视图来set新状态,只需set数据本身
 *      规避了视图状态一致性问题,无需手工判空
 *      无需调用视图,不需要手动写findViewbyId
 *      就算要调用视图,也是通过binding来引用
 *      BindAdapter
 *
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
        initBaseApi();
    }

    protected abstract void initBaseApi();

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
