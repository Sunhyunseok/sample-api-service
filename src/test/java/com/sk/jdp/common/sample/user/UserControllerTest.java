package com.sk.jdp.common.sample.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sk.jdp.common.sample.user.controller.UserController;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserController userController;
	
	@Autowired
	private ObjectMapper objectMapper;

	
	//test method 실행 시 공통 설정
	@BeforeEach()
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.addFilters(new CharacterEncodingFilter("UTF-8",true)).build();
	}
	
	
	@Test
	public void getUserByIdTest() throws Exception {
		//Controller에서 매핑 정의
		mockMvc.perform(get("/user/1"))
			/*응답 검증 내용 정의
			 * 상태값 정의 - OK
			 * content type 정의 - json형식
			 */
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			//처리 내용 출력
			.andDo(print());
	}
	
	@Test
	public void getAllUserTest() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print());
	}
	
	@Test
	public void createUserTest() throws Exception {
		
		ObjectNode content = objectMapper.createObjectNode();
		content.put("name", "test");
		content.put("email","test@test.com");
		content.put("age",20);
		
		mockMvc.perform(post("/user")
				.content(objectMapper.writeValueAsString(content))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print());
	}
	
	
}
