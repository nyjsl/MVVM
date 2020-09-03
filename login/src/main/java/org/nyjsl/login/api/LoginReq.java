package org.nyjsl.login.api;


import androidx.annotation.NonNull;

/**
 * author : weixing
 * date   : 2019-10-3114:05
 */
public class LoginReq {

    private String mobile;
    private String password;

    public LoginReq(@NonNull String mobile, @NonNull String password) {
        this.mobile = mobile;
        this.password = password;
    }

    @NonNull
    public String getMobile() {
        return mobile;
    }

    public void setMobile(@NonNull String mobile) {
        this.mobile = mobile;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
