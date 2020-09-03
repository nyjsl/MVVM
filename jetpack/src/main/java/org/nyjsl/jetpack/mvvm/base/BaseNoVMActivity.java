package org.nyjsl.jetpack.mvvm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author : weixing
 * @date : 2020/8/29 2:36 PM
 */
public abstract class BaseNoVMActivity<DataBinding extends ViewDataBinding> extends BaseActivity {

    protected DataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initView();
        initData();
    }

    private void initDataBinding() {
        binding = initDataBinding(getLayoutId());
        if (null != binding) {
            binding.setLifecycleOwner(this);
            setModel();
            setData();
        }
    }

    protected abstract void setData();

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
