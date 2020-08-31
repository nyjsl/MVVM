package org.nyjsl.theme;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * @author : weixing
 * @date : 2019-11-07 14:09
 */
@StringDef({
        ParamsKey.ACCESS_TOKEN, ParamsKey.REFRESH_TOKEN,
        ParamsKey.APP_VERSION, ParamsKey.SYSTEM_VERSION,
        ParamsKey.BRAND, ParamsKey.MODEL,ParamsKey.APP_SOURCE,
        ParamsKey.UNIQUE_ID, ParamsKey.SYSTEM
})
@Retention(RetentionPolicy.SOURCE)
public @interface ParamsKey {
    String ACCESS_TOKEN = "accessToken";
    String REFRESH_TOKEN = "refreshToken";
    String APP_VERSION = "appVersion";
    String SYSTEM_VERSION = "systemVersion";
    String BRAND = "brand";
    String MODEL = "model";
    String UNIQUE_ID = "uniqueId";
    String SYSTEM = "system";
    String APP_SOURCE = "appSource";

}
