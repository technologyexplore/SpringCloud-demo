package com.eureka.stu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.stu.bean.User;
import com.eureka.stu.service.UserService;

@RestController
@RequestMapping("/user")
public class ConsumerController {
	@Autowired
	UserService userService;
	 
	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public String getuser() {
	   return userService.getuser();
	}
	
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserById(Integer id) {
		 return userService.getUserById(id);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
		 return userService.addUser(user);
	}
	
}
