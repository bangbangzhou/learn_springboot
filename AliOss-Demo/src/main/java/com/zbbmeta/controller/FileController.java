package com.zbbmeta.controller;


import com.zbbmeta.service.AliOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@RestController
@RequestMapping("/alioss/file")
public class FileController {
    @Autowired
    private AliOssService aliOssService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file) {
        String url = aliOssService.upload(file);
        return ResponseEntity.ok(url);
    }

    @DeleteMapping
    public ResponseEntity delete(String url) {
        aliOssService.delete(url);
        return ResponseEntity.ok("删除成功");
    }
}
