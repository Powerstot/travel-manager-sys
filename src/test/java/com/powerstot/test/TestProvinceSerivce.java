package com.powerstot.test;

import com.powerstot.travels.TravelsApplication;
import com.powerstot.travels.entity.Province;
import com.powerstot.travels.service.ProvinceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestProvinceSerivce {
    @Autowired
    private ProvinceService provinceService;

    @Test
    public void testPage() {
        List<Province> pageList = provinceService.findByPage(1, 5);
        pageList.forEach(province -> {
            System.out.println(province);
        });
    }
}
