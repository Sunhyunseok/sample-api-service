package com.sk.jdp.common.sample.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.sk.jdp.common.sample.user.model.User;
import com.sk.jdp.common.sample.user.model.UserSearch;

@Repository
@Mapper
public interface UserMapper {

	public User getUserById(int id);

	public List<User> getAllUser();

	public int createUser(User user);

	public int editUserEmail(User user);

	public int removeUser(int id);
	
	public Page<User> getUserList(UserSearch search);

}
