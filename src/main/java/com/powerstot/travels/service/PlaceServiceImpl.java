package com.powerstot.travels.service;

import com.powerstot.travels.dao.PlaceDao;
import com.powerstot.travels.entity.Place;
import com.powerstot.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{
    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page - 1) * rows;
        return placeDao.findByProvinceIdPage(start, rows, provinceId);
    }

    @Override
    public Integer findByProvinceIdTotal(String provinceId) {
        return placeDao.findByProvinceIdTotal(provinceId);
    }

    @Override
    public void save(Place place) {
        //将place存入
        placeDao.save(place);
    }

    @Override
    public void delete(String placeId) {
        //再将place删除
        placeDao.delete(placeId);
    }

    @Override
    public Place findOne(String id) {
        return placeDao.findOne(id);
    }

    @Override
    public void update(Place place) {
        placeDao.update(place);
    }


}
