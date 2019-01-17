package com.xandone.wcdog.service.impl;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.mapper.ImageMapper;
import com.xandone.wcdog.pojo.ImageBean;
import com.xandone.wcdog.service.ImageService;
import com.xandone.wcdog.utils.FtpClientUtils;
import com.xandone.wcdog.utils.IDUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public ImageBean upfileByUser(MultipartFile file, String userId) throws Exception {
        ImageBean imageBean = null;

        String filename = file.getOriginalFilename(); // 获得原始的文件名
        String newName = IDUtils.RandomId() + filename.substring(filename.lastIndexOf("."));

        InputStream input = file.getInputStream();
        FtpClientUtils a = new FtpClientUtils();
        FTPClient ftp = a.getConnectionFTP(Config.FTP_IP, 21, "ftpuser", "@@22xiao");
        boolean success = a.uploadFile(ftp, Config.FTP_IMAGE_PATH, newName, input);

        if (success) {
            String imgUrl = Config.FTP_HOST_PATH + newName;
            imageBean = new ImageBean();
            imageBean.setImgId(IDUtils.RandomId());
            imageBean.setImgUrl(imgUrl);
            imageBean.setUserId(userId);
            imageBean.setPageViews("0");
            imageBean.setUpTime(new Date());

            System.out.println(imgUrl);
        }

        a.closeFTP(ftp);

        return imageBean;
    }
}
