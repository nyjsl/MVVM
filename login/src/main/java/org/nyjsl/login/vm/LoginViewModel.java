package org.nyjsl.login.vm;

import org.nyjsl.common.entity.login.User;
import org.nyjsl.jetpack.mvvm.base.lifecycle.BaseViewModel;
import org.nyjsl.network.vo.Resource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * @author : weixing
 * @date : 2020/9/2 3:17 PM
 */
public class LoginViewModel extends BaseViewModel<LoginRepository> {

    private String name;
    private String pwd;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPwd() {
        return pwd;
    }

    public void setPwd(@NonNull String pwd) {
        this.pwd = pwd;
    }

    public LoginViewModel(@NonNull LoginRepository repository) {
        super(repository);
    }

    @NonNull
    public LiveData<Resource<User>> login() {
       return repository.login(name,pwd);
    }
}
