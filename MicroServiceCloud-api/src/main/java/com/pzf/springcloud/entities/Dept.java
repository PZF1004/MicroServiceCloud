package com.pzf.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor // 全参构造函数
@NoArgsConstructor // 无参构造函数
@Data // 为每个属性设置set、get方法
@Accessors(chain = true)
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long deptno; // 主键
	private String dname; // 部门名称
	private String db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

	public static void main(String[] args) {
		Dept dept = new Dept();
		dept.setDeptno(11L).setDname("张三").setDb_source("db01");

	}

}
