package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.ImageBean;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageBean upfileByUser(MultipartFile file, String userId) throws Exception;
}
