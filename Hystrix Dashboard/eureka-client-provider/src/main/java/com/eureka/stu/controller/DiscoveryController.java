package com.eureka.stu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
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
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	JSONObject data = new JSONObject();
    	data.put("name", "wen fang wei111111");
    	data.put("address", "guangzhou");
    	data.put("sex", "1");        
        System.out.println(data.toJSONString());
        return data.toJSONString();
//    	 throw new RuntimeException("服务端测试异常！");
    }
    
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserById(Integer id) {
    	ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        log.info("/getuser, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        User usr = new User();
        if(id==1) {        	
            usr.setName("wfw test user 111111111111111");
            usr.setAddress("gz getUserById ");
            usr.setSex(1);
        }
        return usr;
//        throw new RuntimeException("getUserById 服务端测试异常！");
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public User getUserById(@RequestBody User user) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		log.info("/getuser, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		log.info("user"+JSON.toJSONString(user));
		return user;
	}
    
    @RequestMapping(method = RequestMethod.GET, value = "/downService")
	 public String downService() {    	
    	return "服务端调用downService成功....";
    }

}
