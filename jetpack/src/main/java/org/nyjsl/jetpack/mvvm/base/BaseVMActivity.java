package org.nyjsl.jetpack.mvvm.base;

import org.nyjsl.jetpack.mvvm.base.lifecycle.BaseViewModel;
import org.nyjsl.jetpack.mvvm.base.lifecycle.RepoViewModelFactory;
import org.nyjsl.network.repository.BaseRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * @author : weixing
 * @date : 2020/8/29 2:35 PM
 */
public abstract class BaseVMActivity<VM extends BaseViewModel,DataBinding extends ViewDataBinding,R extends BaseRepository>
extends BaseNoVMActivity<DataBinding>{

    public VM getViewModel() {
        return viewModel;
    }

    protected VM viewModel;

    private R repository;

    @Override
    protected DataBinding initDataBinding(int layoutId) {
        DataBinding dataBinding = super.initDataBinding(layoutId);
        this.repository = provideRepository();
        viewModel = initViewModel(getViewModelClass());
        return dataBinding;
    }

    protected abstract @NonNull R provideRepository();

    private VM initViewModel(Class<VM> klazz){
        return (VM) RepoViewModelFactory.getInstance(repository).create(klazz);
    }

    private Class<VM> getViewModelClass(){
        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        Class<VM> result = (Class<VM>) actualTypeArguments[0];
        return result;
    }


}
