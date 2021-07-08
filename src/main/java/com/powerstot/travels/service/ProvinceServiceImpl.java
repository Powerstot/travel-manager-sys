package com.powerstot.travels.service;

import com.powerstot.travels.dao.PlaceDao;
import com.powerstot.travels.dao.ProvinceDao;
import com.powerstot.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        //计算出当前页的所有省份基本信息
        List<Province> provincePage = provinceDao.findByPage(start, rows);
        //计算出当前页每个省份的景点数量
        for (Province province : provincePage) {
            province.setPlaceCounts(placeDao.findByProvinceIdTotal(province.getId()));
        }
        return provincePage;
    }

    @Override
    public Integer findTotals() {
        return provinceDao.findTotals();
    }

    @Override
    public void save(Province province) {
        provinceDao.save(province);
    }

    @Override
    public void delete(String id) {
        provinceDao.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDao.findOne(id);
    }

    @Override
    public void update(Province province) {
        provinceDao.update(province);
    }
}
