package com.sk.jdp.common.sample.s3.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.Bucket;
import com.sk.jdp.common.sample.s3.model.BucketUser;
import com.sk.jdp.common.sample.s3.service.BucketService;



@RestController
public class BucketController {

	private BucketService bucketService;
	
	@Autowired
	BucketController(BucketService bucketService){
		this.bucketService= bucketService;
	}
	
	@GetMapping("/buckets")
	public List<Bucket> getBucketList() throws IOException {
		return this.bucketService.getBucketList();
	}
	
	@PostMapping("/bucket")
	public Bucket createBucket(@RequestBody BucketUser iamUserName) throws IOException {
		return this.bucketService.createBucket(iamUserName.getIamUserName());
	}
	
	//버킷 삭제
	//@DeleteMapping("/bucket")
	//public Bucket deleteBucket(@RequestBody BucketUser iamUserName) throws IOException {
	//	return this.bucketService.deleteBucket(iamUserName.getIamUserName());
	//}
	
	
	@PostMapping("/bucket/file")
	public String uploadFile(@RequestBody BucketUser iamUserName) throws IOException{
		return this.bucketService.uploadFile(iamUserName.getIamUserName(),iamUserName.getFile());
	}
	
}
