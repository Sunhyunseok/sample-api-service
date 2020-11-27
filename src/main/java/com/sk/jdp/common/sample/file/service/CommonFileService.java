package com.sk.jdp.common.sample.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.jdp.common.core.service.BaseService;
import com.sk.jdp.common.sample.file.mapper.CommonFileMapper;
import com.sk.jdp.common.sample.file.model.CommonFile;

@Service
public class CommonFileService extends BaseService{

//	private final CommonFileMapper mapper;
//	
//	public CommonFileService(CommonFileMapper mapper) {
//		this.mapper = mapper;
//	}
//	
	@Autowired
	private CommonFileMapper mapper;
	
	/*
	* 통합첨부파일 상세 조회	
	*/	
	public CommonFile getCommonFile(int fileId) {
		return mapper.getCommonFile(fileId);
	}
	
	/*
	* 통합첨부파일 등록	
	*/	
	public int createCommonFile(CommonFile data) {
		return mapper.insertCommonFile(data);
	}
	
	public String getTest(int fileId) {
		return mapper.getCommonFile(fileId).getFileName();
	}
}
