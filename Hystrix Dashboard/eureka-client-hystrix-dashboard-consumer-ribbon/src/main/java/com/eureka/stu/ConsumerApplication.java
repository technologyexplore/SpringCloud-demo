package com.eureka.stu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@EnableCircuitBreaker //开启断路器功能
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication {
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
//	#调用服务端接口的负载均衡策略，选择一个最小的并发请求的server
	@Bean
	public IRule bestAvailableRule() {
		return new BestAvailableRule();
	}
	
//	@Bean
//    public IRule ribbonRule() {
//        return new RandomRule();//这里配置策略，和配置文件对应
//    }
	
//	@Bean
//	public IRule roundRobinRule() {
//		return new RoundRobinRule();
//	}
	
//	@Bean
//    public IRule weightedResponseTimeRule() {
//        return new WeightedResponseTimeRule();//这里配置策略，和配置文件对应
//    }
	

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}

