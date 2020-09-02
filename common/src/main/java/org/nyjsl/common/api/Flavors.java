package org.nyjsl.common.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * productFlavors
 * @author weixing
 * 每次在BuildConfig中增加一个HOST 就在这里加一个对应的值
 */
@StringDef({Flavors.PROD,Flavors.PRE,Flavors.DEV,Flavors.TEST})
@Retention(RetentionPolicy.SOURCE)
public @interface Flavors {

    /**
     * @author weixing
     * BuildConfig中的
     */
    String PROD = "prod";
    /**
     * @author weixing
     * BuildConfig中的
     */
    String PRE = "pre";
    /**
     * @author weixing
     * BuildConfig中的
     */
    String DEV = "dev";
    /**
     * @author weixing
     * BuildConfig中的
     */
    String TEST = "qa";
}
