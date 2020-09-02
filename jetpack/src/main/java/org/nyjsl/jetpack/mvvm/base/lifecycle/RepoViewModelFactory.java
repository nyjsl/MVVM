package org.nyjsl.jetpack.mvvm.base.lifecycle;

import android.app.Application;

import org.nyjsl.network.repository.BaseRepository;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public final class RepoViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static RepoViewModelFactory sInstance;

    @NonNull
    public static RepoViewModelFactory getInstance(@NonNull BaseRepository baseRepository) {
        if (sInstance == null) {
            sInstance = new RepoViewModelFactory(baseRepository);
        }
        return sInstance;
    }

    private BaseRepository baseRepository;

    public RepoViewModelFactory(@NonNull BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (ViewModel.class.isAssignableFrom(modelClass)) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.getConstructor(BaseRepository.class).newInstance(baseRepository);
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