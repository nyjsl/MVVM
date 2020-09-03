package org.nyjsl.login.api;

import org.nyjsl.common.entity.login.User;
import org.nyjsl.network.vo.ApiResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author : weixing
 * @date : 2020/9/3 4:04 PM
 */
public interface  LoginApi {

    @NonNull
    @POST("login")
    LiveData<ApiResponse<User>> login(@NonNull @Body LoginReq loginReq);
}
