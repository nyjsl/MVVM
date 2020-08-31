package org.nyjsl.network.repository;

import org.nyjsl.network.AppExecutors;
import org.nyjsl.network.util.RateLimiter;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

/**
 * @author : weixing
 * @date : 2020/8/31 3:31 PM
 */
public abstract class BaseRepository<NetBundle extends NetworkBoundResource<ResultType, RequestType>, ResultType, RequestType> {

    private Executor executor;

    private final NetBundle repo ;

    private static final int TIME_OUT = 10;

    protected final RateLimiter repoListRateLimit = new RateLimiter<String>(TIME_OUT, TimeUnit.MINUTES);

    public BaseRepository(@Nullable Executor executor) {
        this.executor = executor;
        this.repo = createRepo();
    }

    public BaseRepository() {
        executor = AppExecutors.getInstance().networkIO();
        this.repo = createRepo();
    }

    protected abstract NetBundle createRepo();


}
