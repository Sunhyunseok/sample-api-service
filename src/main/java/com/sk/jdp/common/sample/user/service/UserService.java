package com.sk.jdp.common.sample.user.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sk.jdp.common.core.model.response.PaginatedResponse;
import com.sk.jdp.common.core.service.BaseService;
import com.sk.jdp.common.sample.user.mapper.UserMapper;
import com.sk.jdp.common.sample.user.model.User;
import com.sk.jdp.common.sample.user.model.UserSearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService extends BaseService {

	
    private final RestTemplate restTemplate;
    private final UserMapper userMapper;

    public UserService(RestTemplate restTemplate, UserMapper userMapper) {
        this.restTemplate = restTemplate;
        this.userMapper = userMapper;
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public int createUser(User user) {
        return userMapper.createUser(user);
    }

    public int editUserEmail(User user) {
        return userMapper.editUserEmail(user);
    }

    public int removeUser(int id) {
        return userMapper.removeUser(id);
    }

    public List<User> getRemoteUser() {
        logger.info("test");
//        return Arrays.asList(restTemplate.getForObject("http://localhost:8081/user", User[].class));
        return restTemplate.exchange("http://localhost:8081/user", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}).getBody();
    }

    public PaginatedResponse<User> getUserList(UserSearch search){
    	PageHelper.startPage(search.getPageNum(), search.getPageSize());
    	Page<User> userList=userMapper.getUserList(search);
    	log.debug("listsize:{}",userList.size());
    	return PaginatedResponse.<User> builder()
    			.page(search.getPageNum())
    			.pages(userList.getPages())
    			.total(userList.getTotal())
    			.results(userList.getResult())
    			.build();
    }
}
