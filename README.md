# 校园二手商城配置教程  


  
## 运行系统所需软件
* idea 2020.3
* mysql 8
* Maven
* Jdk8  
## 系统用到的技术有
* Springboot2
* Mybatis-Plus
* Thymeleaf
* Jquery  
  
## 运行软件
1. 使用idea导入源码，刷新一下mvn
2. 创建并导入数据库文件ershop.sql
2. 找到 aplication.yml 配置文件
3. 修改其 中的ip地址和端口和数据库名称(端口默认是**3306**，数据库默认是**esshop**)
4. 修改数据库用户名
5. 修改数据库密码
6. 运行ApplicationHome

访问 http://localhost:8080 为系统的主页
>测试用户 用户名为daming 密码为daming..

访问 http://localhost:8080/admin 为管理员后台
>测试用户 用户名为daming 密码为daming