package org.nyjsl.jetpack.mvvm.base;

import android.os.Bundle;


import org.nyjsl.jetpack.mvvm.base.decorator.IRouter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : weixing
 * @date : 2020/9/1 6:31 PM
 */
public abstract class BaseActivity extends AppCompatActivity implements IRouter {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (!useDataBingding()) {
            setContentView(layoutId);
        }
    }

    protected abstract @LayoutRes int getLayoutId();
    protected boolean useDataBingding(){ return false; }
}
