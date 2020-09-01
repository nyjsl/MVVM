package org.nyjsl.jetpack.mvvm.base;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author : weixing
 * @date : 2020/8/29 2:36 PM
 */
public abstract class BaseNoVMActivity<DataBinding extends ViewDataBinding> extends BaseActivity {

    protected DataBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        dataBinding = initDataBinding(layoutId);
        if (null != dataBinding) {
            setModel();
        }
        initView();
        initData();
    }

    protected abstract void initData();
    protected abstract void setModel();
    protected abstract void initView();

    protected DataBinding initDataBinding(int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * onCreate中返回布局ID
     * @return
     */
    protected abstract @LayoutRes int getLayoutId();
}
