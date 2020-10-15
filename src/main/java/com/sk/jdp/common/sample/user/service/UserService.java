package com.sk.jdp.common.sample.user.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sk.jdp.common.core.service.BaseService;
import com.sk.jdp.common.sample.user.dao.UserMapper;
import com.sk.jdp.common.sample.user.model.User;

@Service
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

}
