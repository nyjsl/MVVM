package org.nyjsl.login.vm;

import org.nyjsl.network.AppExecutors;
import org.nyjsl.network.repository.BaseRepository;
import org.nyjsl.network.repository.NetworkBoundResource;
import org.nyjsl.network.vo.ApiResponse;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

/**
 * @author : weixing
 * @date : 2020/9/2 3:23 PM
 */
public class LoginRepository extends BaseRepository {

    @NonNull
    @Override
    protected HashMap createRepo() {
        HashMap<String, NetworkBoundResource> result = new HashMap<>();
        LoginRepo loginRepo = new LoginRepo();
        result.put(loginRepo.getTAG(), loginRepo);
        return null;
    }

    public void login(@NonNull String uname, @NonNull String pwd) {
        //
    }

    private class LoginRepo extends NetworkBoundResource{

        @Override
        protected void saveCallResult(Object item) {

        }

        @Override
        protected Boolean shouldFetch(@Nullable Object data) {
            return null;
        }

        @Override
        protected LiveData loadFromDb() {
            return null;
        }

        @Override
        protected LiveData<ApiResponse> createCall() {
            return null;
        }
    }
}
