package org.nyjsl.login.vm;

import org.nyjsl.jetpack.mvvm.base.lifecycle.BaseViewModel;
import org.nyjsl.network.repository.BaseRepository;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/9/2 3:17 PM
 */
public class LoginViewModel extends BaseViewModel {

    public LoginViewModel(@NonNull BaseRepository repository) {
        super(repository);
    }
}
