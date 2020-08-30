package org.nyjsl.jetpack.mvvm.base;

import org.nyjsl.jetpack.mvvm.base.lifecycle.BaseViewModel;

import androidx.databinding.ViewDataBinding;

/**
 * @author : weixing
 * @date : 2020/8/29 2:35 PM
 */
public abstract class BaseActivity<VM extends BaseViewModel,DataBinding extends ViewDataBinding> 
extends BaseNoVMActivity<DataBinding>{
    
    protected VM viewModel;

    @Override
    protected DataBinding initDataBinding(int layoutId) {
        DataBinding dataBinding = super.initDataBinding(layoutId);
        viewModel = initViewModel();
        return dataBinding;
    }

    protected abstract VM initViewModel();
}
