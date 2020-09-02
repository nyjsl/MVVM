package org.nyjsl.network.repository;

import org.nyjsl.network.AppExecutors;
import org.nyjsl.network.util.RateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : weixing
 * @date : 2020/8/31 3:31 PM
 */
public abstract class BaseRepository<NetBundle extends NetworkBoundResource<ResultType, RequestType>, ResultType, RequestType> {


    private final HashMap<String,NetBundle> realRepos ;

    private static final int TIME_OUT = 10;

    protected final RateLimiter repoListRateLimit = new RateLimiter<String>(TIME_OUT, TimeUnit.MINUTES);

    public BaseRepository() {
        this.realRepos = createRepo();
    }

    protected abstract @NonNull HashMap<String,NetBundle> createRepo();


}
