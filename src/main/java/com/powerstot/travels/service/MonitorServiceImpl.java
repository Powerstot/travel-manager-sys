package com.powerstot.travels.service;

import com.powerstot.travels.dao.MonitorDao;
import com.powerstot.travels.entity.Monitor;
import com.powerstot.travels.entity.MonitorCountDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MonitorServiceImpl implements MonitorService{
    @Autowired
    private MonitorDao monitorDao;

    @Override
    public void save(Monitor monitor) {
        monitorDao.save(monitor);
    }

    @Override
    public List<Monitor> findByPage(Integer page, Integer rows, String date) {
        int start = (page - 1) * rows;
        //计算出当前页的所有monitor对象基本信息
        return monitorDao.findByPage(start, rows, date);
    }

    @Override
    public Integer findTotals(String date) {
        return monitorDao.findTotals(date);
    }

    @Override
    public void update(Monitor monitor) {
        monitor.setChecked(true);
        monitorDao.update(monitor);
    }

    @Override
    public void delete(String id) {
        monitorDao.delete(id);
    }

    @Override
    public List<MonitorCountDate> findMonitorTotal() {
        return  monitorDao.findMonitorTotal();
    }

    @Override
    public Integer checkedCount() {
        return monitorDao.checkedCount();
    }

    @Override
    public Integer unCheckedCount() {
        return monitorDao.unCheckedCount();
    }

}
