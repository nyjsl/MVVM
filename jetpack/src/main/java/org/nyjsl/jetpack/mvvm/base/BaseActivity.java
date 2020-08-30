package org.nyjsl.jetpack.mvvm.base;

import com.blankj.utilcode.util.Utils;

import org.nyjsl.jetpack.mvvm.base.lifecycle.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

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
        viewModel = initViewModel(getViewModelClass());
        return dataBinding;
    }

    private VM initViewModel(Class<VM> klazz){
        return ViewModelProvider.AndroidViewModelFactory.getInstance(Utils.getApp()).create(klazz);
    }

    private Class<VM> getViewModelClass(){
        //TODO 需要验证
        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        Class<VM> result = (Class<VM>) actualTypeArguments[0];
        return result;
    }


}
