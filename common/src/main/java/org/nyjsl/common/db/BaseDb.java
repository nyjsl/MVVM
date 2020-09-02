package org.nyjsl.common.db;

import org.nyjsl.common.db.dao.UserDao;
import org.nyjsl.common.entity.login.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author : weixing
 * @date : 2020/9/2 4:13 PM
 */
@Database(entities = {User.class},version = 1)
public abstract class BaseDb extends RoomDatabase {

    public abstract UserDao userDao();

}
