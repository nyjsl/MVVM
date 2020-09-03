package org.nyjsl.network.repository;

import org.nyjsl.network.util.RateLimiter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 3:31 PM
 */
public abstract class BaseRepository<NetBundle extends NetworkBoundResource<ResultType, RequestType>, ResultType, RequestType> {


    private final HashMap<String,NetBundle> realRepos ;

    protected HashMap<String, NetBundle> getRealRepos() {
        return realRepos;
    }

    private static final int TIME_OUT = 10;

    protected final RateLimiter rateLimiter = new RateLimiter<String>(TIME_OUT, TimeUnit.MINUTES);

    public BaseRepository() {
        this.realRepos = createRepo();
    }

    protected abstract @NonNull HashMap<String,NetBundle> createRepo();


}
