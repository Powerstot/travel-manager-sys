package com.powerstot.test;

import com.powerstot.travels.TravelsApplication;
import com.powerstot.travels.entity.Place;
import com.powerstot.travels.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestPlaceService {
    @Autowired
    private PlaceService placeService;

    @Test
    public void findByPageTest() {
        List<Place> places = placeService.findByProvinceIdPage(1, 4, "1");
        places.forEach(System.out::println);
    }

    @Test
    public void findTotalPage() {
        Integer totals = placeService.findByProvinceIdTotal("1");
        System.out.println(totals);
    }
}
