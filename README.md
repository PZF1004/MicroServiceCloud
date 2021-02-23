# MicroServiceCloud

## 父工程MicroServiceCloud

新建父工程MicroServiceCloud，切记是Packageing是pom模式

主要是定义POM文件，将后续各个子模块公用的jar包等统一提出来，类似一个抽象父类。

![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps14.jpg) 

 

## 公共子模块MicroServiceCloud-api

新建工程：

![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps15.jpg)![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps16.jpg) 

Packaging是jar模式

![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps17.jpg) 

子模块建成后可以在父工程的pom文件下看到添加了子模块

![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps18.jpg) 

mvn clean 、mvn install后给其它模块引用，达到通用目的。

也即需要用到部门实体的话，不用每个工程都定义一份，直接引用本模块即可。

 

## 服务提供者MicroServiceCloud-provider-dept-8001

（1）pom.xml文件

（2）application.yml本工程的配置文件

（3）工程src/main/resources目录下新建mybatis文件夹后新建mybatis.cfg.xml文件

（4）MySQL创建部门数据库脚本

DROP DATABASE IF EXISTS cloudDB01;

CREATE DATABASE cloudDB01 CHARACTER SET UTF8;

USE cloudDB01;

CREATE TABLE dept(

 deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

 dname VARCHAR(60),

 db_source  VARCHAR(60)

);

INSERT INTO dept(dname,db_source) VALUES('开发部',DATABASE());

INSERT INTO dept(dname,db_source) VALUES('人事部',DATABASE());

INSERT INTO dept(dname,db_source) VALUES('财务部',DATABASE());

INSERT INTO dept(dname,db_source) VALUES('市场部',DATABASE());

INSERT INTO dept(dname,db_source) VALUES('运维部',DATABASE());

SELECT * FROM dept;

（5）DeptDao部门接口

工程src/main/resources/mybatis目录下新建mapper文件夹后再建DeptMapper.xml

（6）DeptService部门服务接口

DeptServiceImpl部门服务接口实现类

（7）DeptController部门微服务提供者REST

（8）DeptProvider8001_App主启动类

（9）测试

http://localhost:8001/dept/get/2

http://localhost:8001/dept/list

 

## 客户端使用MicroServiceCloud-consumer-dept-80

（1）pom.xml文件

（2）application.yml本工程的配置文件

（3）Spring配置文件：com.pzf.springcloud.cfg.beans包下ConfigBean的编写

（类似spring里面的applicationContext.xml写入的注入Bean）

@Configuration

***\*public\**** ***\*class\**** ConfigBean {

（4）这个是消费端，不需要有service层

（5）Controller层

com.pzf.springcloud.controller包下新建DeptController_Consumer部门微服务消费者REST。

使用restTemplate访问restful接口，从而使这个客户端可以访问服务端需要service层才能访问的数据。

（6）启动DeptConsumer80_App类

（7）测试

http://localhost/consumer/dept/get/2

http://localhost/consumer/dept/list

http://localhost/consumer/dept/add?dname=AI

## Eureka服务注册中心MicroServiceCloud-eureka-7001

（1）pom.xml文件

***\*<\*******\*dependencies\*******\*>\****

​	***\*<!--eureka-server服务端 -->\****

​	***\*<\*******\*dependency\*******\*>\****

​		***\*<\*******\*groupId\*******\*>\*******\*org.springframework.cloud\*******\*</\*******\*groupId\*******\*>\****

​		***\*<\*******\*artifactId\*******\*>\*******\*spring-cloud-starter-eureka-server\*******\*</\*******\*artifactId\*******\*>\****

​	***\*</\*******\*dependency\*******\*>\****

​	***\*<!-- 修改后立即生效，热部署 -->\****

​	***\*<\*******\*dependency\*******\*>\****

​		***\*<\*******\*groupId\*******\*>\*******\*org.springframework\*******\*</\*******\*groupId\*******\*>\****

​		***\*<\*******\*artifactId\*******\*>\*******\*springloaded\*******\*</\*******\*artifactId\*******\*>\****

​	***\*</\*******\*dependency\*******\*>\****

​	***\*<\*******\*dependency\*******\*>\****

​		***\*<\*******\*groupId\*******\*>\*******\*org.springframework.boot\*******\*</\*******\*groupId\*******\*>\****

​		***\*<\*******\*artifactId\*******\*>\*******\*spring-boot-devtools\*******\*</\*******\*artifactId\*******\*>\****

​	***\*</\*******\*dependency\*******\*>\****

***\*</\*******\*dependencies\*******\*>\****

（2）application.yml本工程的配置文件

（3）在EurekaServer7001_App启动类上标注启动该新组件技术Eureka的相关注解

（4）测试eureka的server服务端是否启动成功：需要拼接配置文件中的实例和端口。

![img](D:\workspace\sts-3.9.9.RELEASE\MicroServiceCloud\README.assets\wps19.jpg) 