package org.nyjsl.jetpack.mvvm.base.lifecycle;


import org.nyjsl.network.repository.BaseRepository;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public final class RepoViewModelFactory<R extends BaseRepository> extends ViewModelProvider.NewInstanceFactory {

    private static RepoViewModelFactory sInstance;

    @NonNull
    public static <R extends BaseRepository> RepoViewModelFactory getInstance(@NonNull R repository) {
        if (sInstance == null) {
            sInstance = new RepoViewModelFactory(repository);
        }
        return sInstance;
    }

    private R repository;

    public RepoViewModelFactory(@NonNull R repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (ViewModel.class.isAssignableFrom(modelClass)) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.getConstructor(repository.getClass()).newInstance(repository);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
        return super.create(modelClass);
    }
}