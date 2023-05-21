# tracord_server

### 一、介绍

tracord项目的后端仓库。

### 二、软件架构

##### 1.技术栈

###### 1.1.SpringBoot

###### &nbsp; &nbsp; SpringBoot是一个快速开发框架，可以快速搭建项目，省去了繁琐的配置，实现快速开发。其核心思想是约定大于配置，使用注解的方式进行配置，简化了配置文件的编写。同时，SpringBoot大幅度降低了软件耦合度，方便新增和修改模块。

###### 1.2.MybatisPlus

###### &nbsp; &nbsp; MybatisPlus是国产数据持久化框架，降低了数据库注入的风险，同时提供了代码生成器，可以快速生成代码，提高开发效率。本项目使用MybatisPlus负责mapper层的开发，每个mapper继承BaseMapper，可以直接使用MybatisPlus提供的方法，在进行相对复杂的查询时，可以自定义sql语句。

###### 1.3.Mysql

###### &nbsp; &nbsp; Mysql是一款开源的关系型数据库，本项目使用Mysql进行数据存储。

###### 1.4.Docker

###### &nbsp; &nbsp; Docker是一款轻量级的容器化工具，可以将应用程序和依赖打包成一个独立的镜像，方便部署。本项目使用Docker进行部署，方便后续迭代。

###### 1.5.SpringSecurity

###### &nbsp; &nbsp; SpringSecurity是一款安全框架，可以实现用户认证和授权，本项目考虑使用SpringSecurity进行安全认证和权限控制，增强用户安全性。考虑后续迭代中在登陆部分加上安全认证，使用JWT进行token认证，使用SpringSecurity进行权限控制，使用SpringSecurity进行CSRF防御。

###### 1.6.Redis

###### &nbsp; &nbsp; Redis是一款开源的内存数据库，本项目使用Redis进行缓存，提高系统性能。

##### 2.项目结构

```
├─yicang_app_server
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─mbsnjdxyry
│  │  │  │          └─tracord_backend
│  │  │  │              ├─config
│  │  │  │              ├─controller
│  │  │  │              ├─mapper
│  │  │  │              ├─domain
│  │  │  │              │  └─vo
│  │  │  │              ├─service
│  │  │  │              │  └─impl
│  │  │  │              ├─common
│  │  │  │              ├─utils
│  │  │  │              ├─fliter
│  │  │  │              ├─exception
│  │  │  │              └─TracordBackendApplication.java
│  │  │  └─resources
│  │  │      ├─docker
│  │  │      ├─doc
│  │  │      ├─sql
│  │  │      └─application.yml
```

### 三、软件技术难点

###### 1.使用Redis进行缓存，提高系统性能。

###### 2.后端接口设计

[接口文档](./src/main/resources/doc/踪记.md)

###### 端口设计尽量原子化，方便前端调用。例如：用户信息的增删改查，对应一个接口，后端根据前端传入的参数自动判断进行操作。

###### 3.前后端分离

- 使用http协议进行通信

###### 4.安全性

- 使用SpringSecurity进行安全认证
- 使用JWT进行token认证
- 使用SpringSecurity进行权限控制

### 四、开发进度

- [x] 1.0.0 2023-05-21 完成基本功能开发
- [x] 2.0.0 2023-05-22 完成安全性功能开发