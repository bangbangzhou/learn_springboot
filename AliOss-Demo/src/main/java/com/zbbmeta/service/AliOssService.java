package com.zbbmeta.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.zbbmeta.property.AliOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Service
public class AliOssService {
    @Autowired
    private OSS oss;

    @Autowired
    private AliOssProperties properties;

    /**
     * 上传文件
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        // 原文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 构造新的文件名，名字不重复
        String objectName = UUID.randomUUID().toString() + suffix;

        // 上传文件
        try {
           ;
            // 创建PutObject请求。
            oss.putObject(properties.getBucketName(), objectName, file.getInputStream());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (Exception ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }



        return properties.getEndpoint() + "/" + properties.getBucketName() + "/" + objectName;
    }

    /**
     * 删除文件
     * @param url
     */
    public void delete(String url){

            oss.deleteObject(properties.getBucketName(), url);

    }

}
