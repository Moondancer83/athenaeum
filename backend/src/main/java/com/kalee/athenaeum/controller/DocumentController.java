package com.kalee.athenaeum.controller;

import java.util.Collections;
import java.util.List;

import com.kalee.athenaeum.data.Document;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentController {

    @GetMapping("/api/documents")
    @ResponseBody
    public List<Document> list() {
        return Collections.emptyList();
    }

    @GetMapping("/api/documents/{id:.+}")
    @ResponseBody
    public byte[] download(@PathVariable(required = true) long id) {
        return Long.toString(id).getBytes();
    }

    @PostMapping("/api/documents")
    @ResponseBody
    public Document upload(@RequestParam("file") MultipartFile file) {
        return null;
    }

}