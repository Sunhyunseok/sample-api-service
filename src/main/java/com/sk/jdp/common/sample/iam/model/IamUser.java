package com.sk.jdp.common.sample.iam.model;




import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IamUser {
	//iam user 계정명
	private String iamUserName;
	//계정 생성 날짜
	private Date createDate;
	
	
	
}
