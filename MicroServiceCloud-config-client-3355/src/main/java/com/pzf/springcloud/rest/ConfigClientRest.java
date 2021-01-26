package com.pzf.springcloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

	@Value("${spring.application.name}") //获取配置文件中相应的信息==>MicroServiceCloud-config-client
	private String applicationName;

	@Value("${eureka.client.service-url.defaultZone}") // ==>http://eureka-test.com:7001/eureka/
	private String eurekaServers;

	@Value("${server.port}") //==>8201
	private String port;

	@RequestMapping("/config")
	public String getConfig() {
		String str = "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
		System.out.println("******str: " + str);
		return "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
	}
}
