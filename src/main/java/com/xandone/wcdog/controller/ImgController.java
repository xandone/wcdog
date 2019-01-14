package com.xandone.wcdog.controller;

import com.xandone.wcdog.pojo.Base.BaseBean;
import com.xandone.wcdog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/img")
public class ImgController {
    @Autowired
    ImageService imageService;

    @RequestMapping("/upImage")
    @ResponseBody
    public BaseBean upfileByUser(@RequestParam(value = "file") MultipartFile file, String userId) throws Exception {
        BaseBean baseResult = new BaseBean();
        return baseResult;

    }
}
