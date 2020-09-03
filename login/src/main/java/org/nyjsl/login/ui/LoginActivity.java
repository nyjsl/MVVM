package org.nyjsl.login.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.sankuai.waimai.router.annotation.RouterUri;

import org.nyjsl.common.entity.login.User;
import org.nyjsl.common.router.RouterPath;
import org.nyjsl.jetpack.mvvm.base.BaseVMActivity;
import org.nyjsl.login.R;
import org.nyjsl.login.databinding.LoginActivityLoginBindingImpl;
import org.nyjsl.login.vm.LoginRepository;
import org.nyjsl.login.vm.LoginViewModel;
import org.nyjsl.network.vo.Resource;

@RouterUri(path = RouterPath.Login.LOGIN_ACT)
public class LoginActivity extends BaseVMActivity<LoginViewModel,LoginActivityLoginBindingImpl,LoginRepository> {

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setData() {
    }

    @Override
    protected void initData() {
        viewModel.setName("13657230465");
        viewModel.setPwd("230465");
        //Observe 才会触发
        viewModel.login().observe(this, new Observer<Resource<User>>() {
            @Override
            public void onChanged(Resource<User> userResource) {
                //TODO ?
            }
        });
    }

    @Override
    protected void setModel() {
        binding.setModel(this);
    }

    @Override
    protected void initView() {

    }

    public void goHome() {
        startUri(this,RouterPath.HOME.HOME_ACT);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @NonNull
    @Override
    protected LoginRepository provideRepository() {
        return new LoginRepository();
    }
}