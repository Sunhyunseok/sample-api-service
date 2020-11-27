package com.sk.jdp.common.sample.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sk.jdp.common.sample.file.service.ExcelService;
import com.sk.jdp.common.sample.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/upload")
	public void fileUpload(HttpServletRequest request, @RequestParam("excelFile") MultipartFile multipartFile) {
		log.debug("java.io.tmpdir={}", System.getProperty("java.io.tmpdir"));
		excelService.excelUpload(multipartFile);
	}
	
}
