package com.xandone.wcdog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xandone.wcdog.pojo.Base.BaseBean;
import com.xandone.wcdog.pojo.ImageBean;
import com.xandone.wcdog.pojo.ImageLayBean;
import com.xandone.wcdog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/img")
public class ImgController {
    @Autowired
    ImageService imageService;
    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/upImage")
    @ResponseBody
    public String upfileByUser(@RequestParam(value = "file") MultipartFile file, String userId) throws Exception {
        ImageLayBean baseResult = new ImageLayBean();

        if (file == null) {
            System.out.println("上传文件为空");
            return "";
        }
        ImageBean imageBean = imageService.upfileByUser(file, userId);
        baseResult.setCode(0);
        baseResult.setMsg("sucesse");
        ImageLayBean.Data data = new ImageLayBean.Data(imageBean.getImgUrl(), "图片");
        baseResult.setData(data);

        String json = objectMapper.writeValueAsString(baseResult);

        System.out.println(json);

        return json;

    }
}
