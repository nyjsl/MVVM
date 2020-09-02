package org.nyjsl.common.service;

import org.nyjsl.common.db.dao.UserDao;

/**
 * @author : weixing
 * @date : 2020/9/2 4:31 PM
 */
public interface IDbService {

    UserDao userDao();
}
