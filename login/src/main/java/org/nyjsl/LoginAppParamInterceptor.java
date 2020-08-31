package org.nyjsl;

import android.os.Build;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;

import org.nyjsl.network.interceptors.BasicParamsIntercptor;
import org.nyjsl.theme.ParamsKey;

import java.util.HashMap;

import androidx.annotation.NonNull;

/**
 * @author : weixing
 * @date : 2020/8/31 4:57 PM
 */
public final class LoginAppParamInterceptor extends BasicParamsIntercptor {

    /**
     * 系统：1、IOS ,2、安卓 ,3、其它
     */
    private static final String SYSTEM_ANDROID = "2";
    /**
     * 系统：1、app 2 小程序
     */
    private static final String APP_SOURCE_APP = "1";

    @NonNull
    @Override
    protected HashMap<String, String> getParamsMap() {
        HashMap<String, String> basicParamsMap = new HashMap<>();
        basicParamsMap.put(ParamsKey.APP_VERSION, AppUtils.getAppVersionName());
        basicParamsMap.put(ParamsKey.SYSTEM_VERSION, android.os.Build.VERSION.RELEASE);
        basicParamsMap.put(ParamsKey.BRAND, Build.BRAND);
        basicParamsMap.put(ParamsKey.MODEL, Build.MODEL);
        basicParamsMap.put(ParamsKey.SYSTEM, SYSTEM_ANDROID);
        basicParamsMap.put(ParamsKey.APP_SOURCE, APP_SOURCE_APP);
        basicParamsMap.put(ParamsKey.UNIQUE_ID, DeviceUtils.getUniqueDeviceId());
        return basicParamsMap;
    }
}
