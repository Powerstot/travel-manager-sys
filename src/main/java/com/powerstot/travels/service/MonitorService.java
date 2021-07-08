package com.powerstot.travels.service;

import com.powerstot.travels.entity.Monitor;
import com.powerstot.travels.entity.MonitorCountDate;

import java.util.List;
import java.util.Map;

public interface MonitorService {
    void save(Monitor monitor);

    //page：当前页 rows：页面条数
    List<Monitor> findByPage(Integer page, Integer rows, String date);

    //查询总条数
    Integer findTotals(String date);

    //修改监控记录
    void update(Monitor monitor);

    void delete(String id);

    List<MonitorCountDate> findMonitorTotal();

    Integer checkedCount();

    Integer unCheckedCount();

}
