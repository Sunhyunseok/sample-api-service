package com.sk.jdp.common.sample.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.jdp.common.sample.user.model.User;

@Mapper
public interface UserMapper {

    public User getUserById(int id);

    public List<User> getAllUser();

    public int createUser(User user);

    public int editUserEmail(User user);

    public int removeUser(int id);

}
