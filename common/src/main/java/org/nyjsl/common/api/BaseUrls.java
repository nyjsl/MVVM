package org.nyjsl.common.api;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author weixing
 */
public class BaseUrls {

    private static HashMap<String,String> hostMap = new HashMap<>(4);

    /**
     * 根据类型获取对应主机地址
     * @param fieledName
     * @return
     */
    public static String getHostByName(@Host String fieledName){
        String result = hostMap.get(fieledName);
        if (TextUtils.isEmpty(result)) {
            throw new IllegalArgumentException("can not find host,is your name correct or not inited");
        }
        return result;
    }

    /**
     *根据buildConfig初始化各种host
     * @param buildConfigClass 主工程的buildConfig
     */
    public static void init(Class buildConfigClass){
        try {
            initHostsMapByRefrect(buildConfigClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private static final String HOST_PREFIX = "HOST_";

    private static void initHostsMapByRefrect(Class buildConfigClass) throws IllegalAccessException {
        Field[] fields = buildConfigClass.getFields();
        if (null == fields) {
            return;
        }
        for (Field field : fields) {
            String name = field.getName();
            if(name.startsWith(HOST_PREFIX)){
                String host;
                host = (String) field.get(null);
                if (!TextUtils.isEmpty(host)) {
                    hostMap.put(name, host);
                }
            }
        }
    }




}
