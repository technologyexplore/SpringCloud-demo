package com.eureka.stu.exception;

import org.springframework.stereotype.Component;

import com.eureka.stu.bean.User;
import com.eureka.stu.service.UserService;

@Component
public class FeignClientFallBack implements UserService{

	@Override
	public String getuser() {

		return "eureka-client-provider 服务宕机了-------";
	}

	@Override
	public User getUserById(Integer id) {
		User user = new User();
		user.setName("eureka-client-provider 服务宕机了-------");
		return user;
	}

	@Override
	public User addUser(User user) {
		User user1 = new User();
		user1.setName("addUser eureka-client-provider 服务宕机了-------");
		return user1;
	}

}
