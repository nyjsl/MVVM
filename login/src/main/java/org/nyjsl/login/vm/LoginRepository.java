package org.nyjsl.login.vm;


import org.nyjsl.common.api.RetrofiManagerProxy;
import org.nyjsl.common.db.dao.UserDao;
import org.nyjsl.common.entity.login.User;
import org.nyjsl.common.service.ServiceManager;
import org.nyjsl.login.api.LoginApi;
import org.nyjsl.login.api.LoginReq;
import org.nyjsl.login.api.LoginService;
import org.nyjsl.network.repository.BaseRepository;
import org.nyjsl.network.repository.NetworkBoundResource;
import org.nyjsl.network.util.AbsentLiveData;
import org.nyjsl.network.vo.ApiResponse;
import org.nyjsl.network.vo.Resource;

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
        return result;
    }

    @NonNull
    public LiveData<Resource<User>> login(@NonNull String uname, @NonNull String pwd) {
        LoginRepo loginRepo = (LoginRepo) getRealRepos().get(LoginRepo.class.getName());
        if (null != loginRepo) {
            loginRepo.setUname(uname);
            loginRepo.setPwd(pwd);
            return loginRepo.asLiveData();
        }
        return AbsentLiveData.create();
    }

    private class LoginRepo extends NetworkBoundResource<User,User>{

        private UserDao userDao;
        
        private String uname;
        private String pwd;

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public LoginRepo() {
            userDao = ServiceManager.getDBService().userDao();
        }

        @Override
        protected void saveCallResult(User item) {
            userDao.insert(item);
        }

        @Override
        protected Boolean shouldFetch(@Nullable User data) {
            return null == data;
        }

        @Override
        protected LiveData<User> loadFromDb() {
            return userDao.getByMobile(uname);
        }

        @Override
        protected LiveData<ApiResponse<User>> createCall() {
            return LoginService.getApi().login(new LoginReq(uname, pwd));
        }
    }
}
