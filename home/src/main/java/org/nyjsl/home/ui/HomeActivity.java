package org.nyjsl.home.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.nyjsl.common.router.RouterPath;
import org.nyjsl.home.R;
import org.nyjsl.jetpack.mvvm.base.BaseActivity;

import androidx.annotation.NonNull;

@Route(path = RouterPath.HOME.HOME_ACT)
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_home;
    }
}