package org.nyjsl.common.db;


import com.blankj.utilcode.util.Utils;

import org.nyjsl.common.constatns.DBConstants;

import java.util.HashMap;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author : weixing
 * @date : 2020/9/2 4:43 PM
 */
public final class DbFactory {

    private static HashMap<String, RoomDatabase> dbTable = new HashMap(4);

    public static RoomDatabase create(String name) {
        RoomDatabase result;
        if (dbTable.containsKey(name)) {
            result = dbTable.get(name);

        } else {
            switch (name) {
                case DBConstants.DBName.ZEUS:
                    result = Room.databaseBuilder(Utils.getApp(),
                            BaseDb.class, name).build();
                    break;
                default:
                    result = null;
                    break;
            }
            dbTable.put(name, result);
        }
        return result;
    }

}
