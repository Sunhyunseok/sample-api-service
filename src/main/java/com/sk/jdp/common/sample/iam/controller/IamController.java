package com.sk.jdp.common.sample.iam.controller;

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<String> getIAMUser() throws IOException {
		return this.iamService.getIAMUser();
	}
	//user 생성
	@PostMapping("/iam/user")
	public String createIAMUser(@RequestBody IamUser user) throws IOException {
		return this.iamService.createIAMUser(user.getUserName());
	}
	
	//access key 생성 및 할당 
	@PostMapping("/iam/access-key")
	public String createAccessKey(@RequestBody IamUser user) throws IOException {
		return this.iamService.createIAMAccessKey(user.getUserName());
	}
	
	 //policy 생성 (독립 s3버킷  full access)
	@PostMapping("/iam/policy") 
	 public String createIAMPolicy(@RequestBody IamUser user) throws IOException { 
		 return this.iamService.createIAMPolicy(user.getUserName());
	 }
	 
}
