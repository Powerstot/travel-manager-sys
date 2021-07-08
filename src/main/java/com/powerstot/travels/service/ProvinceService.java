package com.powerstot.travels.service;


import com.powerstot.travels.entity.Province;

import java.util.List;

public interface ProvinceService {

    //page：当前页 rows：页面条数
    List<Province> findByPage(Integer page, Integer rows);

    //查询总条数
    Integer findTotals();

    //保存省份
    void save(Province province);

    //删除省份
    void delete(String id);

    //查找一个省份
    Province findOne(String id);

    //修改省份
    void update(Province province);
}
