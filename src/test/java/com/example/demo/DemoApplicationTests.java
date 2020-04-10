package com.example.demo;

import com.example.demo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private UserDao userDao;

	@Test
	void contextLoads() {

	}

}
