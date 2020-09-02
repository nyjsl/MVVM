package org.nyjsl.common.service;

import com.sankuai.waimai.router.Router;

import org.nyjsl.common.constatns.ServiceKeys;

/**
 * @author : weixing
 * @date : 2020/9/2 4:32 PM
 */
public class ServiceManager {

    public static IDbService getDBService() {
        return Router.getService(IDbService.class, ServiceKeys.SINGLETON);
    }
}
