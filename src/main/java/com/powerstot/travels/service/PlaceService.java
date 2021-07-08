package com.powerstot.travels.service;

import com.powerstot.travels.entity.Place;

import java.util.List;

public interface PlaceService {
    List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId);

    Integer findByProvinceIdTotal(String provinceId);

    void save(Place place);

    void delete(String id);

    Place findOne(String id);

    void update(Place place);

}
