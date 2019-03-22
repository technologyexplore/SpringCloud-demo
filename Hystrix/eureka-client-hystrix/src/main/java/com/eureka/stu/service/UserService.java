package com.eureka.stu.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eureka.stu.bean.User;
import com.eureka.stu.exception.FeignClientFallBack;

@FeignClient(value="eureka-client-provider",path="/user",fallback = FeignClientFallBack.class)
public interface UserService {

     @RequestMapping(method = RequestMethod.GET, value = "/getuser")
	 public String getuser();
     
     @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
     public User getUserById(@RequestParam(value="id") Integer id);
     
     @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
 	 public User addUser(@RequestBody User user);
     
}
