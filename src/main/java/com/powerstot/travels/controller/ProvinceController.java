package com.powerstot.travels.controller;

import com.powerstot.travels.entity.Province;
import com.powerstot.travels.entity.Result;
import com.powerstot.travels.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("province")
@Slf4j
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * 修改省份信息
     * @param province
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.update(province);
            result.setMsg("点击确定之后回到省份列表");
        } catch(Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("修改省份信息失败!");
        }
        return result;
    }


    /**
     * 查找一个省份信息
     * @param id
     * @return
     */
    @GetMapping("findOne")
    public Province findOne(String id) {
        return provinceService.findOne(id);
    }


    /**
     * 删除省份信息
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result save(String id) {
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("点击确定之后刷新省份列表");
        } catch(Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("删除省份信息失败!");
        }
        return result;
    }


    /**
     * 保存省份信息
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("点击确定之后跳转到省份列表");
        } catch(Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("添加省份信息失败!");
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
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        //初始化page和rows
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;

        Map<String, Object> map = new HashMap<>();
        //分页查到的省份信息
        List<Province> provinces = provinceService.findByPage(page, rows);
        //总条数
        Integer totals = provinceService.findTotals();
        //总页数
        int totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces", provinces);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }

}
