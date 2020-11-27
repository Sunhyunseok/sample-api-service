package com.sk.jdp.common.sample.file.controller;

import java.util.List;

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

import com.sk.jdp.common.core.controller.BaseController;
import com.sk.jdp.common.sample.file.model.CommonFile;
import com.sk.jdp.common.sample.file.service.CommonFileService;
import com.sk.jdp.common.sample.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	CommonFileService commonFileService;
	
	
	@GetMapping("/files")
	public CommonFile getCommonFile(@RequestParam("fileId") int fileId) {
		return commonFileService.getCommonFile(fileId);
	}
	
	@PostMapping("/upload")
	public List<String> fileUpload(HttpServletRequest request, @RequestParam("fileName") List<MultipartFile> multipartFile){
		log.debug("java.io.tmpdir={}",System.getProperty("java.io.tmpdir"));
		return fileService.fileUpload(multipartFile);
	}
	
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> getFile(@PathVariable int fileId) throws Exception {
		log.debug("java.io.tmpdir={}",System.getProperty("java.io.tmpdir"));
		Resource file = fileService.fileDownload(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	
}
