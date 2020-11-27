package com.sk.jdp.common.sample.file.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sk.jdp.common.core.exception.ApiErrorException;
import com.sk.jdp.common.core.service.BaseService;
import com.sk.jdp.common.sample.file.model.CommonFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FileService extends BaseService {
	
	@Value("${fileupload.path}")
	private String fileUploadPath;
	private final Path root = Paths.get("C:/project/fileupload");
	
	
	@Autowired
	CommonFileService commonFileService;
		

	public List<String> fileUpload(List<MultipartFile> multipartFile){
		List<String> fileList=new ArrayList<>();
		try {
			for(MultipartFile mFile:multipartFile) {
				if(!mFile.isEmpty()) {
					File tmp = new File(fileUploadPath + File.separator + mFile.getOriginalFilename());
					mFile.transferTo(tmp);
					
					log.debug("getAbsolutePath={}",tmp.getAbsolutePath());
					
					CommonFile commonFile = new CommonFile();
					commonFile.setFileName(tmp.getName());
					commonFile.setFilePath(tmp.getPath());
					commonFile.setFileSizeByte(tmp.length());
					commonFile.setFileExtsn(tmp.getName().substring(tmp.getName().lastIndexOf(".") + 1));
					
					commonFileService.createCommonFile(commonFile);
					
					fileList.add(commonFile.getFileName());
				}
			}
			
		} catch(IOException ex) {
			log.error("file upload error", ex);
			throw new ApiErrorException("error.file.0001");
		}
		
		return fileList;
	}	
	
	
	
	public Resource fileDownload(int fileId) throws Exception {
		
		try {
			CommonFile commonFile = commonFileService.getCommonFile(fileId);
	
			Path file = root.resolve(commonFile.getFileName());
			Resource resource = new UrlResource(file.toUri());
			
			if (resource.exists()||resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
	
}
