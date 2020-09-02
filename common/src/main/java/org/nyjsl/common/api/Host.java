package org.nyjsl.common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * productFlavors
 * @author weixing
 * 每次在BuildConfig中增加一个HOST 就在这里加一个对应的值
 */
@StringDef({Host.USER})
@Retention(RetentionPolicy.SOURCE)
public @interface Host {

    /**
     * @author weixing
     * BuildConfig中的
     */
    String USER = "HOST_USER";
}
