package org.nyjsl.login.ui;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import com.sankuai.waimai.router.annotation.RouterUri;

import org.nyjsl.common.router.RouterPath;
import org.nyjsl.jetpack.mvvm.base.BaseVMActivity;
import org.nyjsl.login.R;
import org.nyjsl.login.databinding.LoginActivityLoginBindingImpl;
import org.nyjsl.login.vm.LoginRepository;
import org.nyjsl.login.vm.LoginViewModel;
import org.nyjsl.network.repository.BaseRepository;

@RouterUri(path = RouterPath.Login.LOGIN_ACT)
public class LoginActivity extends BaseVMActivity<LoginViewModel,LoginActivityLoginBindingImpl> {

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setModel() {
        dataBinding.setModel(this);
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
    protected BaseRepository getBaseRepository() {
        return new LoginRepository();
    }
}