package com.powerstot.travels.controller;

import com.powerstot.travels.entity.Monitor;
import com.powerstot.travels.entity.MonitorCountDate;
import com.powerstot.travels.entity.Result;
import com.powerstot.travels.service.MonitorService;
import com.powerstot.travels.utils.ImageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.ServerSocket;
import java.util.*;

@RestController
@RequestMapping("monitor")
@CrossOrigin
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("findCheckCount")
    public Map<String, Integer> findCheckCount() {
        Map<String, Integer> map = new HashMap<>();
        map.put("checkedCount", monitorService.checkedCount());
        map.put("unCheckedCount", monitorService.unCheckedCount());
        return map;
    }


    @GetMapping("findMonitorTotal")
    public List<MonitorCountDate> findMonitorTotal() {
        // Map<String, Object> map = new HashMap<>();
        List<MonitorCountDate> monitorTotal = monitorService.findMonitorTotal();
        for (MonitorCountDate monitorCountDate : monitorTotal) {
            //日期退后一天
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(monitorCountDate.getDate());
            calendar.add(Calendar.DATE, 1);
            monitorCountDate.setDate(calendar.getTime());
        }
        return monitorTotal;
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try{
            monitorService.delete(id);
            result.setMsg("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("删除失败！");
        }
        return result;
    }

    /**
     * 处理记录
     * @param monitor
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Monitor monitor) {
        Result result = new Result();
        try{
            monitorService.update(monitor);
            result.setMsg("处理成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("处理失败！");
        }
        return result;
    }

    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows, String date) {
        //初始化page和rows
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;

        Map<String, Object> map = new HashMap<>();
        //分页查到的信息
        List<Monitor> monitors = monitorService.findByPage(page, rows, date);
        //总条数
        Integer totals = monitorService.findTotals(date);
        //总页数
        int totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("monitors", monitors);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }

    /**
     * 启动监控摄像头线程
     * @return
     */
    @RequestMapping("save")
    public Result save() {
        Result result = new Result();
        try {
            ImageServer.monitorService = monitorService;
            //先将线程的服务端口new出来
            ImageServer.ss = new ServerSocket(9000);
            new Thread(new ImageServer());
            result.setMsg("开始监测！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("无法开启摄像线程！");
        }
        return result;
    }

    /**
     * 关闭监控摄像头线程
     * @return
     */
    @RequestMapping("offSave")
    public Result offSave() {
        Result result = new Result();
        try {
            System.out.println("server closed");
            //将端口直接关闭
            ImageServer.ss.close();
            result.setMsg("停止监测！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("无法关闭摄像线程！");
        }
        return result;
    }


}
