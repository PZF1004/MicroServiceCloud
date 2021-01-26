package com.pzf.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {
	@Bean
	public IRule myRule() {
		// return new RandomRule(); //Ribbon默认是轮询，自定义为随机（使用的是人家的规则）
		return new RandomRule_PZF(); //自定义为每台机器5次（使用的是自己的规则）
	}
}
