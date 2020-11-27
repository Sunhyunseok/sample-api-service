package com.sk.jdp.common.sample.iam.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.AttachUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.AttachUserPolicyResult;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.CreatePolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreatePolicyResult;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;
import com.sk.jdp.common.sample.iam.model.IamUser;

import software.amazon.awssdk.services.iam.model.IamException;


/**
 * @ClassName IamService.java
 * @Description Iam 리소스에 접근하여 계정 조회, 생성 및 생성된 정책 매핑
 */
@Service
public class IamService {
	
//	@Value("${cloud.aws.credentials.accessKey}")
//    private String accessKey;
//	
//    @Value("${cloud.aws.credentials.secretKey}")
//    private String secretKey;
  
    
    public AmazonIdentityManagement buildIamClient() {
    	
//    	AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    	AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
//	 			.withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .withRegion(Regions.AP_NORTHEAST_2)
	            .withCredentials(new ProfileCredentialsProvider("jdpprofile"))
	            .build();
    	return iam;
    }
    
    //get IAMuser List
    public List<IamUser> getIamUserList(){
    	
    	AmazonIdentityManagement iam = buildIamClient();
    	ListUsersRequest request = new ListUsersRequest();
    	List<IamUser> userList =  new ArrayList<>();
    	
    	boolean done = false;
	   
	    while(!done) {
	        ListUsersResult response = iam.listUsers(request);

	        for(User user : response.getUsers()) {
	        	IamUser iamUser = new IamUser();
	        	iamUser.setIamUserName(user.getUserName());
	        	iamUser.setCreateDate(user.getCreateDate());
	        	userList.add(iamUser);
	        	System.out.format("Retrieved user %s", user.getUserName());
	        }

	        request.setMarker(response.getMarker());

	        if(!response.getIsTruncated()) {
	            done = true;
	        }
	    }
	    
	    return userList;
    }
   
        
        
	//IAM user create
	public String createIamUser(String iamUserName) {

		
		AmazonIdentityManagement iam = buildIamClient();
	 	
	 	try {
           
	 		//CreateUserRequest request = CreateUserRequest.builder().userName(username).build();
	 		CreateUserRequest request = new CreateUserRequest().withUserName(iamUserName);
            //CreateUserResponse response = iam.createUser(request);
	 		CreateUserResult response = iam.createUser(request);

	 		return response.getUser().getUserName();

        } catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
	 	return "";
    }
	
	//User IAM AccessKey create
	public String createIamAccessKey(String iamUserName) {
		  
		 
		AmazonIdentityManagement iam = buildIamClient();
		
	 	try {
	        	
            
            CreateAccessKeyRequest request = new CreateAccessKeyRequest().withUserName(iamUserName);

            CreateAccessKeyResult response = iam.createAccessKey(request);
            
            String keyId = response.getAccessKey().getAccessKeyId();
           //
            // System.out.println(response.accessKey().secretAccessKey());
            return keyId;

	        } catch (IamException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }
	        return "";
	    }
		
	public String PolicyDocument(String iamUserName) {
		String policydocument =
				"{" +
                "  \"Version\": \"2012-10-17\",	" +
                "  \"Statement\": [				" +
                "    {" +
                "    	\"Effect\": \"Allow\","+
                "    	\"Action\": [" +
                "			\"s3:GetBucketLocation\", "+
                "      		\"s3:ListAllMyBuckets\" "+
                "  		 ]," +
                "   	\"Resource\": \"arn:aws:s3:::*\" "+
                "	},"+
                "   {"+
                "       \"Effect\": \"Allow\",	" +
                "       \"Action\": \"s3:*\"," +
                "		\"Resource\": ["+
                "			\"arn:aws:s3:::jdpbucket"+iamUserName+"\", "+
                "			\"arn:aws:s3:::jdpbucket"+iamUserName+"/*\""+
				"		 ]"+
                "    }" +
                "   ]" +
                "}";
		
		return policydocument;

	}
			
           
	
	public String createIamPolicy(String iamUserName) {
		//AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
	 	//AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
		// 			.withCredentials(new AWSStaticCredentialsProvider(credentials))
		//            .withRegion(Regions.AP_NORTHEAST_2)
		//            .build();
		//String username = userName;
		//Region region = Region.AWS_GLOBAL;
		//IamClient iam = IamClient.builder().region(region).build();

		AmazonIdentityManagement iam = buildIamClient();
		
		String policyName= "JDPpolicy"+iamUserName;
		String policydocument = PolicyDocument(iamUserName);
        try {
              //CreatePolicyRequest request = CreatePolicyRequest.builder()
              //  .policyName(policyName)
              //  .policyDocument(policydocument).build();
              
        	CreatePolicyRequest request = new CreatePolicyRequest()
                      .withPolicyName(policyName)
                      .withPolicyDocument(policydocument);

              //CreatePolicyResponse response = iam.createPolicy(request);
        	CreatePolicyResult response = iam.createPolicy(request);
        	
        	String policyArn = "arn:aws:iam::739913306696:policy/"+policyName;
              
//			AmazonIdentityManagement client = AmazonIdentityManagementClientBuilder.standard().build();
			AttachUserPolicyRequest policyrequest = new AttachUserPolicyRequest().withUserName(iamUserName).withPolicyArn(policyArn);
			AttachUserPolicyResult policyresponse = iam.attachUserPolicy(policyrequest);
			  
			return response.getPolicy().getArn();
			
         } catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
	}
    
}