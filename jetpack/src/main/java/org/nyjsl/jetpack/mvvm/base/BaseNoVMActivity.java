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
        initDataBinding();
        initView();
        initData();
    }

    private void initDataBinding() {
        dataBinding = initDataBinding(getLayoutId());
        if (null != dataBinding) {
            setModel();
        }
    }

    @Override
    protected boolean useDataBingding() {
        return true;
    }

    protected abstract void initData();
    protected abstract void setModel();
    protected abstract void initView();

    protected DataBinding initDataBinding(int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

}
