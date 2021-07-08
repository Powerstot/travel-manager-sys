package com.powerstot.travels.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T, K>{ // T代表对象，K代表主键
    void save(T t);
    void update(T t);
    void delete(K k);
    T findOne(K k);
    Integer findTotals();
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
}
