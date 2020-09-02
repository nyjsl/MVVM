package org.nyjsl.common.service.impl;

import com.sankuai.waimai.router.annotation.RouterProvider;
import com.sankuai.waimai.router.annotation.RouterService;

import org.nyjsl.common.constatns.DBConstants;
import org.nyjsl.common.constatns.ServiceKeys;
import org.nyjsl.common.db.BaseDb;
import org.nyjsl.common.db.DbFactory;
import org.nyjsl.common.db.dao.UserDao;
import org.nyjsl.common.service.IDbService;

import androidx.room.Room;

/**
 * @author : weixing
 * @date : 2020/9/2 4:35 PM
 */
@RouterService(interfaces = IDbService.class, key = ServiceKeys.SINGLETON,singleton = true)
public class DbServiceImpl implements IDbService {

    private volatile BaseDb db;

    public DbServiceImpl() {
        if (null == db) {
            synchronized (DbServiceImpl.class) {
                if (null == db) {
                    db = (BaseDb) DbFactory.create(DBConstants.DBName.ZEUS);
                }
            }
        }
    }

    @RouterProvider
    public static DbServiceImpl provideService() {
        return new DbServiceImpl();
    }

    @Override
    public UserDao userDao() {
        return db.userDao();
    }
}
