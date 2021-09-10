#### SpringBoot API Seed

基于Springboot、Mybatis-plus实现了一个基础后台服务脚手架项目，集成了Swagger UI、knife4j文档工具，实现业务的快速开发和扩展。

------



1. #### 技术栈及相关资料

- ##### SpringBoot : https://docs.spring.io/spring-boot/docs/2.4.10/reference/html/

- ##### Spring Security : https://docs.spring.io/spring-security/site/docs/current/reference/html5/

- ##### Mybatis-Plus : https://baomidou.com/

- ##### Hutool : https://www.hutool.cn/docs/#/

- ##### Swagger : https://swagger.io/

- ##### Knife4j : https://doc.xiaominfo.com/

- ##### Docker : https://docs.docker.com/docker-hub/

- ##### JWT : https://jwt.io/


2. #### 项目结构说明

   ```
   ├── HELP.md
   ├── README.md
   ├── mvnw
   ├── mvnw.cmd
   ├── package											# Linux打包安装脚本集
   │   ├── bin											# Jar包存放目录,项目根目录下执行mvn clean package后会拷贝一个副本到该目录下
   │   ├── conf
   │   │   └── application.yml							# Spring Boot外置配置文件模板
   │   ├── init_db.sh									# 数据库初始化脚本
   │   ├── ins_pkgs.sh									# 在线安装软件包脚本
   │   ├── ins_web.sh									# 部署Web页面脚本
   │   ├── install.sh									# 一键安装脚本
   │   ├── package.sh									# 一键打包脚本
   │   ├── profile.sh									# 服务信息脚本
   │   ├── scripts									
   │   │   └── log.sh									# 控制台输出美化脚本
   │   ├── service
   │   │   └── springboot-api-seed.service				# systemd service
   │   ├── sql
   │   │   ├── create_table.sql						# 建表脚本
   │   │   └── insert.sql								# 插入基础数据脚本
   │   ├── uninstall.sh								# 卸载服务脚本
   │   └── web								
   │       ├── web.conf								# nginx配置文件
   │       └── web_files.tar.gz						# 静态页面压缩包
   ├── pom.xml
   ├── springboot-api-seed.iml	
   ├── src
   │   ├── main
   │   │   ├── docker
   │   │   │   ├── Dockerfile							# Docker Build Config
   │   │   │   └── application.yml						# Spring Boot外置配置文件模板
   │   │   ├── java
   │   │   │   └── com
   │   │   │       └── vanwei
   │   │   │           └── tech
   │   │   │               ├── Application.java		# 启动入口
   │   │   │               ├── annotation
   │   │   │               │   ├── AnonymousAccess.java	# 声明接口可匿名访问的自定义注解类
   │   │   │               │   └── Log.java				# 声明接口需要打印出请求信息的自定义注解类
   │   │   │               ├── config
   │   │   │               │   ├── JacksonConfig.java		# jackson配置
   │   │   │               │   ├── MybatisAutoConfig.java	# Mybatis plus配置
   │   │   │               │   ├── SchedulingConfig.java	# 定时任务配置
   │   │   │               │   ├── SecurityConfig.java		# Spring Security配置
   │   │   │               │   ├── SwaggerConfig.java		# Swagger配置
   │   │   │               │   ├── ThreadPoolConfig.java	# 线程池配置
   │   │   │               │   └── WebMvcConfig.java		# Web MVC配置
   │   │   │               ├── constant
   │   │   │               │   ├── CommonConstants.java	# 通用常量集合
   │   │   │               │   └── SecurityConstants.java	
   │   │   │               ├── controller
   │   │   │               │   ├── RoleController.java		# 角色API
   │   │   │               │   └── UserController.java 	# 用户API
   │   │   │               ├── core
   │   │   │               │   ├── BaseEntity.java					# 基础实体类
   │   │   │               │   ├── BaseQueryRequestDTO.java		# 基础分页查询DTO类
   │   │   │               │   ├── EntityMetaObjectHandler.java	# 实体元数据处理器
   │   │   │               │   ├── GlobalExceptionHandler.java		# 全局异常处理器
   │   │   │               │   ├── JavaTimeModule.java				
   │   │   │               │   ├── LogAspect.java					# 接口日志切面工具
   │   │   │               │   └── SqlFilterArgumentResolver.java  
   │   │   │               ├── dto									# DTO
   │   │   │               │   ├── RoleDTO.java
   │   │   │               │   ├── UserDTO.java
   │   │   │               │   └── request
   │   │   │               │       ├── RoleQueryRequestDTO.java
   │   │   │               │       ├── RoleRequestDTO.java
   │   │   │               │       ├── UserLoginRequestDTO.java
   │   │   │               │       ├── UserQueryRequestDTO.java
   │   │   │               │       └── UserRequestDTO.java
   │   │   │               ├── entity								# Entity 数据库实体
   │   │   │               │   ├── Role.java
   │   │   │               │   ├── User.java
   │   │   │               │   └── UserRole.java
   │   │   │               ├── enums								# 枚举
   │   │   │               │   └── RoleEnum.java
   │   │   │               ├── exception
   │   │   │               │   ├── JwtSignatureVerifyException.java
   │   │   │               │   └── ServiceException.java
   │   │   │               ├── mapper								# Mybatis Mapper Interfaces
   │   │   │               │   ├── RoleMapper.java
   │   │   │               │   ├── UserMapper.java
   │   │   │               │   └── UserRoleMapper.java
   │   │   │               ├── properties							# Spring Config Properties
   │   │   │               │   └── JwtProperties.java
   │   │   │               ├── runner								# Command Line Runner
   │   │   │               │   ├── InitAdminRunner.java
   │   │   │               │   └── InitDefaultRoleRunner.java
   │   │   │               ├── security							# Spring Security
   │   │   │               │   ├── CustomAccessDeniedEntryHandler.java
   │   │   │               │   ├── CustomAuthenticationEntryPoint.java
   │   │   │               │   ├── CustomUserDetailsService.java
   │   │   │               │   ├── JwtAuthenticationTokenFilter.java
   │   │   │               │   ├── JwtPayload.java
   │   │   │               │   └── SecurityUser.java
   │   │   │               ├── service								# Service
   │   │   │               │   ├── RoleService.java
   │   │   │               │   ├── UserRoleService.java
   │   │   │               │   ├── UserService.java
   │   │   │               │   └── impl
   │   │   │               │       ├── RoleServiceImpl.java
   │   │   │               │       ├── UserRoleServiceImpl.java
   │   │   │               │       └── UserServiceImpl.java
   │   │   │               ├── util								# Utils
   │   │   │               │   ├── AuthUtils.java
   │   │   │               │   ├── ClassUtils.java
   │   │   │               │   ├── HttpClientUtils.java
   │   │   │               │   ├── JwtUtil.java
   │   │   │               │   ├── Result.java
   │   │   │               │   ├── SecurityUtils.java
   │   │   │               │   ├── SpringContextHolder.java
   │   │   │               │   ├── TreeNode.java
   │   │   │               │   └── TreeUtils.java
   │   │   │               └── vo									# View Objects
   │   │   │                   ├── RoleVO.java
   │   │   │                   ├── UserInfoVO.java
   │   │   │                   └── UserVO.java
   │   │   └── resources
   │   │       ├── config											# Application Config
   │   │       │   ├── application-dev.yml
   │   │       │   ├── application-prod.yml
   │   │       │   ├── application-test.yml
   │   │       │   ├── application.yml
   │   │       │   └── logback-spring.xml							# logback Config
   │   │       └── mapper
   │   │           ├── RoleMapper.xml
   │   │           ├── UserMapper.xml
   │   │           └── UserRoleMapper.xml
   │   └── test
   │       └── java
   │           └── com
   │               └── vanwei
   │                   └── tech
   │                       ├── ApplicationTests.java
   │                       └── generator
   │                           └── GeberatorUIServer.java			# 在线页面生成基础Entity、Mapper等等类的工具类
   ```


3. ####  项目打包部署

   ##### (1) Linux在线安装

   ```bash
   # Jar打包
   mvn clean package
   
   cd package
   
   # 安装包打包
   ./package
   
   scp -P port springboot-api-seed.0.0.1-SNAPSHOT.tar.gz root@IP:/root/
   
   # 安装部署
   tar -xvf springboot-api-seed.0.0.1-SNAPSHOT.tar.gz
   
   cd springboot-api-seed
   
   ./install.sh
   ```

   ##### (2) Docker打包推送镜像

   ```bash
   mvn clean pakcage
   
   # 连接的是外置的数据库
   docker run -d -p 8080:8080 -v ./logs/:/var/logs/ --name springboot-api-seed springboot-api-seed:0.0.1-SNAPSHOT
   ```


4. ####  接口文档

   ##### Swagger UI :  http://localhost:8081/swagger-ui/index.html 

   ##### Knife4j UI(支持离线文档导出) :  http://localhost:8081/doc.html 