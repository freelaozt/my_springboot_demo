package com.fxd.dao;

import org.apache.ibatis.annotations.Mapper;

public interface Dao {

    /**
     * 对数据库操作
     * 在mapper创建数据库操作的dao.xml
     */
    int queryTestDatabase(Long id);
}
