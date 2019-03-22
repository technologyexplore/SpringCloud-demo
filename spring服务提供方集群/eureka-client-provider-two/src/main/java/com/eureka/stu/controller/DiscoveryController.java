package com.eureka.stu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.eureka.stu.bean.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class DiscoveryController {
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
    @Value("${server.port}")
    private String ip;
    
    @GetMapping("/client")
    public String client() {
        String services = "Services: " + discoveryClient.getServices()+" ip :"+ip;
        
        System.out.println(services);
        return services;
    }
    
    @GetMapping("/getuser")
    public String getuser() {
//    	try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}    	
    	
    	JSONObject data = new JSONObject();
    	data.put("name", "wen fang wei22222222222");
    	data.put("address", "guangzhou");
    	data.put("sex", "1");
        
        System.out.println(data.toJSONString());
        return data.toJSONString();
    }
    
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserById(Integer id) {
    	ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        log.info("/getuser, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        User usr = new User();
        if(id==1) {        	
        	 usr.setName("wfw test user 222222222222222");
             usr.setAddress("gz getUserById ");
             usr.setSex(1);
        }
        return usr;
    }

}
