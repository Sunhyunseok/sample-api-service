package com.sk.jdp.common.sample.iam.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.jdp.common.sample.iam.model.IamUser;
import com.sk.jdp.common.sample.iam.service.IamService;



@RestController
public class IamController {


	private IamService iamService;
	
	@Autowired
	IamController(IamService iamService){
		this.iamService= iamService;
	}
	
	@GetMapping("/iam/user")
	public List<IamUser> getIamUser() throws IOException {
		return this.iamService.getIamUser();
	}
	

	//user 생성
	@PostMapping("/iam/user")
	public String createIamUser(@RequestBody IamUser user) throws IOException {
		return this.iamService.createIamUser(user.getIamUserName());
	}
	
	//access key 생성 및 할당 
	@PostMapping("/iam/access-key")
	public String createAccessKey(@RequestBody IamUser user) throws IOException {
		return this.iamService.createIamAccessKey(user.getIamUserName());
	}
	
	 //policy 생성 (독립 s3버킷  full access)
	@PostMapping("/iam/policy") 
	 public String createIamPolicy(@RequestBody IamUser user) throws IOException { 
		 return this.iamService.createIamPolicy(user.getIamUserName());
	 }
	 
}
