package com.sk.jdp.common.sample.s3.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class BucketService {


    
    public AmazonS3 buildS3Client() {
    	
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        		.withRegion(Regions.AP_NORTHEAST_2)
        		.withCredentials(new ProfileCredentialsProvider("jdpprofile"))
        		.build();
        return s3;
    }
    
    public List<Bucket> getBucketList() {
    	AmazonS3 s3 = buildS3Client();
    	List<Bucket> buckets = s3.listBuckets();
    	return buckets;
    }
    
	public Bucket getBucket(String bucketName) {
		
		AmazonS3 s3 = buildS3Client();
        
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucketName)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }
	
	public Bucket createBucket(String iamUserName) {
		
		AmazonS3 s3 = buildS3Client();
		
		String bucketName = "jdpbucket"+iamUserName;
        
        Bucket b = null;
        if (s3.doesBucketExistV2(bucketName)) {
            System.out.format("Bucket %s already exists.\n", bucketName);
            b = getBucket(bucketName);
        } else {
            try {
                b = s3.createBucket(bucketName);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }
	


    public String uploadFile(String iamUserName, File file) {
    	
    	AmazonS3 s3 = buildS3Client();
        
    	String bucketName = "jdpbucket"+iamUserName;
    	
    	PutObjectRequest request = new PutObjectRequest(bucketName,file.getName(),file);
    	
    	s3.putObject(request);
    	
    	return "";
    	
    }

}  
