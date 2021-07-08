package com.powerstot.travels.controller;

import com.powerstot.travels.entity.Place;
import com.powerstot.travels.entity.Province;
import com.powerstot.travels.entity.Result;
import com.powerstot.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("place")
@CrossOrigin
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Value("${upload.dir}")
    private String realPath;

    /**
     * 修改景点信息
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @PostMapping("update")
    public Result update(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        try {
            //要先将图片转化为base64编码
            place.setPicPath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            //获取后缀
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            pic.transferTo(new File(realPath, newFileName));
            //place对象上传
            placeService.update(place);

            result.setMsg("景点修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 查找一个省份信息
     * @param id
     * @return
     */
    @GetMapping("findOne")
    public Place findOne(String id) {
        return placeService.findOne(id);
    }


    /**
     * 删除景点
     * @param placeId
     * @return
     */
    @GetMapping("delete")
    public Result delete(String placeId) {
        Result result = new Result();
        try{
            placeService.delete(placeId);
            result.setMsg("删除成功!");
        }catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("删除失败!");
        }
        return result;
    }


    /**
     * 保存景点
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @PostMapping("save")
    public Result save(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        System.out.println(pic.getOriginalFilename());
        System.out.println(place);
         try {
             //文件上传
             String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
             //获取后缀
             String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
             //要先将图片转化为base64编码
             place.setPicPath(Base64Utils.encodeToString(pic.getBytes()));
             pic.transferTo(new File(realPath, newFileName));
             //place对象上传
             placeService.save(place);

             result.setMsg("景点添加成功!");
         } catch (Exception e) {
             e.printStackTrace();
             result.setStatus(false).setMsg(e.getMessage());
         }

        return result;
    }


    /**
     * 根据省份id查询景点
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows, String provinceId) {
        Map<String, Object> map = new HashMap<>();

        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;

        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);
        Integer totals = placeService.findByProvinceIdTotal(provinceId);
        Integer totalPage = totals / rows == 0 ? totals / rows : totals / rows + 1;
        map.put("totalPage", totalPage);
        map.put("places", places);
        map.put("totals", totals);
        map.put("page", page);
        return map;
    }
}
