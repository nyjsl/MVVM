package org.nyjsl.network;

import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.ThreadUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 10:41 AM
 * 全局线程池
 */
public class AppExecutors {

    public static AppExecutors getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final AppExecutors INSTANCE = new AppExecutors(
                ThreadUtils.getSinglePool(),ThreadUtils.getIoPool(),new MainThreadExecutor());
    }

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final Executor mMainThread;

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public Executor mainThread() {
        return mMainThread;
    }


    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
