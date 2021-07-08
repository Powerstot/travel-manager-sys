package com.powerstot.travels.dao;

import com.powerstot.travels.entity.Monitor;
import com.powerstot.travels.entity.MonitorCountDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MonitorDao extends BaseDao<Monitor, String>{

    List<Monitor> findByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("date") String date);

    Integer findTotals(String date);

    List<MonitorCountDate> findMonitorTotal();

    Integer checkedCount();

    Integer unCheckedCount();
}
