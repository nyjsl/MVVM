package org.nyjsl;

import com.tencent.mmkv.MMKV;

import org.nyjsl.network.interceptors.AuthInterceptor;
import org.nyjsl.theme.ParamsKey;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 4:58 PM
 */
public final class LoginAuthInterceptor extends AuthInterceptor {
    @NonNull
    @Override
    protected HashMap<String, String> getAuthHeaderMap() {
        HashMap<String,String> tokenMap = new HashMap<>(2);
        String acccessToken = MMKV.defaultMMKV().decodeString(ParamsKey.ACCESS_TOKEN);
        tokenMap.put(ParamsKey.ACCESS_TOKEN, acccessToken);
        String refreshToken = MMKV.defaultMMKV().decodeString(ParamsKey.REFRESH_TOKEN);
        tokenMap.put(ParamsKey.REFRESH_TOKEN, refreshToken);
        return tokenMap;
    }

    @Override
    protected void updateAuthTokenToLocal(@NonNull HashMap<String, String> tokenMap) {
        for(Map.Entry<String,String> entrySet:tokenMap.entrySet()){
            MMKV.defaultMMKV().encode(entrySet.getKey(),  entrySet.getValue());
        }
    }
}
