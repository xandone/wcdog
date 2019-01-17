package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.ImageBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：xandone
 * created on  ：2019/1/16 9:52
 * description：
 */
public interface ImageService {

    ImageBean upfileByUser(MultipartFile file, String userId) throws Exception;
}
