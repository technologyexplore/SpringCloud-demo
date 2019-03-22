package com.eureka.stu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class ConsumerController {
	 @Autowired
	 RestTemplate restTemplate;
	 
	 @Autowired  
	 private LoadBalancerClient loadBalancerClient;  
	
	 @RequestMapping(value = "/getuser", method = RequestMethod.GET)
	 public String add() {
		 this.loadBalancerClient.choose("eureka-client-provider");//随机访问策略
	     return restTemplate.getForEntity("http://EUREKA-CLIENT-PROVIDER/user/getuser", String.class).getBody();
	 }
	
}
