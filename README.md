# mumu-mall

基于Spring Boot的电商项目后端API

项目接口文档：https://shimo.im/docs/K3WhpQ33RcqvkdyD/read

项目类型：个人项目

开发时间：2021.07～2021.08

*具体功能见接口文档*

### 项目技术栈
+ Spring Boot - 2.5.3
+ MyBatis - 2.2.0
+ MySQL - 8.0.25
+ Redis
+ log4j
+ Swagger3
+ PageHelper分页插件
+ Java Validation API

### 项目结构
+ common：存放公用类。如返回值类型、常量类
+ config：存放过滤器、Redis缓存、Swagger3等等的配置文件
+ controller：给前端提供访问API的类
+ exception：存放项目自定义异常类
+ filter：存放过滤器类
+ model：存放与数据库操作有关的Java Bean和Mapper
+ query：存放查询结果类
+ service：存放业务处理类
+ util：存放工具类
+ vo：存放查询结果返回类

