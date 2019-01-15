package com.xandone.wcdog.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtils {

    /**
     * 获得连接FTP方式
     *
     * @param hostName FTP服务器地址
     * @param port     FTP服务器端口
     * @param userName FTP登录用户名
     * @param passWord FTP登录密码
     * @return FTPClient
     */
    public FTPClient getConnectionFTP(String hostName, int port, String userName, String passWord) {
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try {
            // 连接FTP服务器
            ftp.connect(hostName, port);
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            // 登录ftp
            ftp.login(userName, passWord);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 关闭连接FTP方式
     *
     * @param ftp FTPClient对象
     * @return boolean
     */
    public boolean closeFTP(FTPClient ftp) {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
                System.out.println("ftp已经关闭");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 上传文件FTP方式
     *
     * @param ftp         FTPClient对象
     * @param path        FTP服务器上传地址
     * @param fileName    本地文件路径
     * @param inputStream 输入流
     * @return boolean
     */
    public boolean uploadFile(FTPClient ftp, String path, String fileName, InputStream inputStream) {
        boolean success = false;
        try {
            ftp.changeWorkingDirectory(path);// 转移到指定FTP服务器目录
            FTPFile[] fs = ftp.listFiles();// 得到目录的相应文件列表
            fileName = FtpClientUtils.changeName(fileName, fs);
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            path = new String(path.getBytes("GBK"), "ISO-8859-1");
            ftp.changeWorkingDirectory(path);

            // 设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.storeFile(fileName, inputStream);
            // 关闭输入流
            inputStream.close();
            // 退出ftp
            ftp.logout();
            // 表示上传成功
            success = true;
            System.out.println("上传成功。。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败。。。。。。" + e.toString());
        }
        return success;
    }

    /**
     * 删除文件FTP方式
     *
     * @param ftp      FTPClient对象
     * @param path     FTP服务器上传地址
     * @param fileName FTP服务器上要删除的文件名
     * @return
     */
    public boolean deleteFile(FTPClient ftp, String path, String fileName) {
        boolean success = false;
        try {
            ftp.changeWorkingDirectory(path);// 转移到指定FTP服务器目录
            fileName = new String(fileName.getBytes("GBK"), "ISO88591");
            path = new String(path.getBytes("GBK"), "ISO88591");
            ftp.deleteFile(fileName);
            ftp.logout();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 上传文件FTP方式
     *
     * @param ftp       FTPClient对象
     * @param path      FTP服务器上传地址
     * @param fileName  本地文件路径
     * @param localPath 本里存储路径
     * @return boolean
     */
    public boolean downFile(FTPClient ftp, String path, String fileName, String localPath) {
        boolean success = false;
        try {
            ftp.changeWorkingDirectory(path);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles(); // 得到目录的相应文件列表
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "\\" + ff.getName());
                    OutputStream outputStream = new FileOutputStream(localFile);
                    // 将文件保存到输出流outputStream中
                    ftp.retrieveFile(new String(ff.getName().getBytes("GBK"), "ISO88591"), outputStream);
                    outputStream.flush();
                    outputStream.close();
                    System.out.println("下载成功");
                }
            }
            ftp.logout();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 判断是否有重名文件
     *
     * @param fileName
     * @param fs
     * @return
     */
    public static boolean isFileExist(String fileName, FTPFile[] fs) {
        for (int i = 0; i < fs.length; i++) {
            FTPFile ff = fs[i];
            if (ff.getName().equals(fileName)) {
                return true; // 如果存在返回 正确信号
            }
        }
        return false; // 如果不存在返回错误信号
    }

    /**
     * 根据重名判断的结果 生成新的文件的名称
     *
     * @param fileName
     * @param fs
     * @return
     */
    public static String changeName(String fileName, FTPFile[] fs) {
        int n = 0;
        // fileName = fileName.append(fileName);
        while (isFileExist(fileName.toString(), fs)) {
            n++;
            String a = "[" + n + "]";
            int b = fileName.lastIndexOf(".");// 最后一出现小数点的位置
            int c = fileName.lastIndexOf("[");// 最后一次"["出现的位置
            if (c < 0) {
                c = b;
            }
            StringBuffer name = new StringBuffer(fileName.substring(0, c));// 文件的名字
            StringBuffer suffix = new StringBuffer(fileName.substring(b + 1));// 后缀的名称
            fileName = name.append(a) + "." + suffix;
        }
        return fileName.toString();
    }

}
