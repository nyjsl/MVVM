package org.nyjsl.common.db.dao;

import org.nyjsl.common.entity.login.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author : weixing
 * @date : 2020/9/2 4:26 PM
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM user WHERE id= :id")
    LiveData<User> getByid(String id);
    @Query("SELECT * FROM user WHERE mobile= :mobile")
    LiveData<User> getByMobile(String mobile);

    @Delete
    void delete(User user);
}
