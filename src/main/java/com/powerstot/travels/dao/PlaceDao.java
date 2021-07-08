package com.powerstot.travels.dao;

import com.powerstot.travels.entity.Place;
import com.powerstot.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlaceDao extends BaseDao<Place, String> {

    List<Place> findByProvinceIdPage(@Param("start") Integer start,  @Param("rows") Integer rows, @Param("provinceId") String provinceId);

    Integer findByProvinceIdTotal(String provinceId);

    void delete(String placeId);

    Place findOne(String placeId);

    void update(Place place);

}
