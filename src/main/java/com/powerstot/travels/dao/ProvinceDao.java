package com.powerstot.travels.dao;

import com.powerstot.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProvinceDao extends BaseDao<Province, String>{
}
