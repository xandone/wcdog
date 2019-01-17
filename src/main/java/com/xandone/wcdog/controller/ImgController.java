package com.xandone.wcdog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xandone.wcdog.pojo.ImageBean;
import com.xandone.wcdog.pojo.ImageLayBean;
import com.xandone.wcdog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：xandone
 * created on  ：2019/1/14 10:20
 * description：
 */
@Controller
@RequestMapping("/img")
public class ImgController {
    @Autowired
    ImageService imageService;
    private ObjectMapper objectMapper = new ObjectMapper();

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
        baseResult.setMsg("success");
        ImageLayBean.Data data = new ImageLayBean.Data(imageBean.getImgUrl(), "图片");
        baseResult.setData(data);

        String json = objectMapper.writeValueAsString(baseResult);
        System.out.println(json);

        return json;

    }
}
