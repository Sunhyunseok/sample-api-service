package com.sk.jdp.common.sample.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sk.jdp.common.sample.file.model.CommonFile;

@Repository
@Mapper
public interface CommonFileMapper {

	public CommonFile getCommonFile(int fileId);
	
	public int insertCommonFile(CommonFile data);
}
