package com.pzf.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigBean {  // ConfigBean <==> Spring的配置文件applicationContext.xml
	@Bean
	@LoadBalanced //负载均衡
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IRule myRule() {
		// return new RoundRobinRule(); //默认的轮询（不写默认是这个）
		// return new RandomRule();// 随机
		return new RetryRule();
	}
}
