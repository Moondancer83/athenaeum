package com.kalee.athenaeum.controller;

import java.io.IOException;
import java.util.List;

import com.kalee.athenaeum.data.Document;
import com.kalee.athenaeum.data.S3Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private S3Service s3Service;


    @GetMapping("/api/documents")
    @ResponseBody
    public List<Document> list() {
        logger.info("list");
        return s3Service.list();
    }

    @GetMapping("/api/documents/{id:.+}")
    @ResponseBody
    public byte[] download(@PathVariable(required = true) String id) {
        // I use the object name as ID for the moment
        logger.info("download: {}", id);
        return s3Service.retrieve(id).getData();

    }

    @PostMapping("/api/documents")
    @ResponseBody
    public Document upload(@RequestParam("file") MultipartFile file) throws IOException {
        Document doc = new Document(file.getOriginalFilename(), file.getBytes());
        logger.info("upload: {}", doc);
        String docUrl = s3Service.upload(doc);
        doc.setUrl(docUrl);
        return doc;

    }

}