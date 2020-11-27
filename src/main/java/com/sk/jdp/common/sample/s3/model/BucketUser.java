package com.sk.jdp.common.sample.s3.model;


import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BucketUser {

	//bucket에 매핑될 iam 계정명
	private String iamUserName;
	//파일 
	private File file;
	

}
