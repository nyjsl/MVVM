package org.nyjsl.login.api;

import org.nyjsl.common.api.Host;
import org.nyjsl.common.api.RetrofiManagerProxy;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/9/3 4:11 PM
 */
public class LoginService {

    @NonNull
    public static LoginApi getApi(){
       return  RetrofiManagerProxy.getInstance().getRetrofitService(LoginApi.class, Host.USER);
    }
    
}
