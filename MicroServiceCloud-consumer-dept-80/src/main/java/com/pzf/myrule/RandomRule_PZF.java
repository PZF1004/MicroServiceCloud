package com.pzf.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class RandomRule_PZF extends AbstractLoadBalancerRule {
	private int total = 0; // 总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0; // 当前提供服务的机器号

	/**
	 * 核心：返回具体的服务
	 */
	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			return null;
		}
		Server server = null;
		while (server == null) {
			if (Thread.interrupted()) { // 线程中断
				return null;
			}
			List<Server> upList = lb.getReachableServers(); // 获取可到达的服务
			List<Server> allList = lb.getAllServers(); // 获取所有的服务

			int serverCount = allList.size();
			if (serverCount == 0) {
				return null;
			}
			if (total < 5) { // total<5次，+1
				server = upList.get(currentIndex);
				total++;
			} else { // total>=5次，currentIndex+1
				total = 0;
				currentIndex++;
				if (currentIndex >= upList.size()) { //服务的机器号超过服务数量，服务的机器号置0
					currentIndex = 0;
				}
			}
			if (server == null) {
				Thread.yield();
				continue;
			}
			if (server.isAlive()) {
				return (server);
			}
			server = null;
			Thread.yield();
		}
		return server;
	}
	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}
	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {

	}
}