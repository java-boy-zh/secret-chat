# 密聊项目

## 项目介绍
这是一个基于Spring Cloud微服务架构的即时通讯系统，提供安全可靠的在线聊天功能。系统采用分布式架构设计，支持高并发、高可用，并提供了丰富的功能特性。

## 技术栈
- **后端框架**：Spring Boot 3.0.13 + Spring Cloud 2022.0.4
- **微服务组件**：Spring Cloud Alibaba 2022.0.0.0
- **数据库**：MySQL 8.3.0
- **ORM框架**：MyBatis-Plus 3.5.5
- **缓存**：Redis 7.2.4
- **消息队列**：RabbitMQ 3.13
- **注册中心**：Nacos 2.3.1
- **对象存储**：MinIO 2024.8.29
- **其他工具**：
  - ZooKeeper 3.9.2
  - Gson 2.8.9
  - Guava 31.0.1-jre
  - Netty（用于实时通讯）

## 项目结构
```
secret-chat/
├── secret-chat-api/        # 接口服务模块
│   ├── secret-chat-auth/   # 认证服务
│   ├── secret-chat-base/   # 基础服务
│   ├── secret-chat-file/   # 文件服务
│   ├── secret-chat-netty/  # 实时通讯服务
│   ├── secret-chat-ai/     # AI服务
│   └── secret-chat-word/   # 文档服务
├── secret-chat-common/     # 公共工具模块
├── secret-chat-gateway/    # 网关服务模块
├── secret-chat-pojo/       # 数据模型模块
└── sql/                    # 数据库脚本
```

## 环境要求
- JDK 17+
- Maven 3.6+
- Docker 环境（用于部署相关服务）
- 操作系统：支持Windows/Linux/MacOS

## 快速开始

### 1. 环境准备
所有服务均已配置Docker部署方案，详细部署步骤请参考 `Docker.md`：
- MySQL 8.3.0
- Redis 7.2.4
- Nacos 2.3.1
- MinIO 2024.8.29
- RabbitMQ 3.13
- ZooKeeper 3.9.2

### 2. 配置修改
- 修改数据库连接信息
- 修改Redis连接信息
- 修改Nacos配置信息
- 配置MinIO存储信息
- 配置RabbitMQ连接信息
# 密聊项目

## 项目介绍
这是一个基于Spring Cloud微服务架构的即时通讯系统，提供安全可靠的在线聊天功能。系统采用分布式架构设计，支持高并发、高可用，并提供了丰富的功能特性。

## 技术栈
- **后端框架**：Spring Boot 3.0.13 + Spring Cloud 2022.0.4
- **微服务组件**：Spring Cloud Alibaba 2022.0.0.0
- **数据库**：MySQL 8.3.0
- **ORM框架**：MyBatis-Plus 3.5.5
- **缓存**：Redis 7.2.4
- **消息队列**：RabbitMQ 3.13
- **注册中心**：Nacos 2.3.1
- **对象存储**：MinIO 2024.8.29
- **其他工具**：
  - ZooKeeper 3.9.2
  - Gson 2.8.9
  - Guava 31.0.1-jre
  - Netty（用于实时通讯）

## 项目结构
```
secret-chat/
├── secret-chat-api/        # 接口服务模块
│   ├── secret-chat-auth/   # 认证服务
│   ├── secret-chat-base/   # 基础服务
│   ├── secret-chat-file/   # 文件服务
│   ├── secret-chat-netty/  # 实时通讯服务
│   ├── secret-chat-ai/     # AI服务
│   └── secret-chat-word/   # 文档服务
├── secret-chat-common/     # 公共工具模块
├── secret-chat-gateway/    # 网关服务模块
├── secret-chat-pojo/       # 数据模型模块
└── sql/                    # 数据库脚本
```

## 环境要求
- JDK 17+
- Maven 3.6+
- Docker 环境（用于部署相关服务）
- 操作系统：支持Windows/Linux/MacOS

## 快速开始

### 1. 环境准备
所有服务均已配置Docker部署方案，详细部署步骤请参考 `Docker.md`：
- MySQL 8.3.0
- Redis 7.2.4
- Nacos 2.3.1
- MinIO 2024.8.29
- RabbitMQ 3.13
- ZooKeeper 3.9.2

### 2. 配置修改
- 修改数据库连接信息
- 修改Redis连接信息
- 修改Nacos配置信息
- 配置MinIO存储信息
- 配置RabbitMQ连接信息

### 3. 编译部署
```bash
# 编译项目
mvn clean package

# 启动服务
# 1. 启动Nacos
# 2. 启动网关服务
# 3. 启动其他微服务
```

## 功能特性

### 核心功能
- 安全的即时通讯
  - 端到端加密
  - 消息防篡改
  - 消息防重放
- 文件传输
  - 支持大文件传输
  - 断点续传
  - 文件加密存储
- 群聊支持
  - 群组管理
  - 群消息同步
  - 群成员管理
- 消息加密
  - 非对称加密
  - 对称加密
  - 密钥管理
- 离线消息
  - 消息持久化
  - 消息同步
  - 消息状态追踪

### 扩展功能
- AI智能助手
  - 智能对话
  - 内容生成
  - 知识问答
- 文档处理
  - 文档预览
  - 文档转换
  - 文档协作
- 系统管理
  - 用户管理
  - 权限控制
  - 系统监控

## 安全特性
- 用户认证与授权
- 数据加密传输
- 敏感信息保护
- 访问控制
- 操作审计

## 性能优化
- 消息队列削峰
- 缓存优化
- 数据库优化
- 网络传输优化
- 并发处理优化

## 开发团队
- 项目维护者：[王青玄]
- 技术支持：[王青玄]
- 贡献者：[王青玄]

## 联系方式
- 项目地址：[[项目地址](https://github.com/java-boy-zh/secret-chat)]
- 文档地址：[[文档地址](https://github.com/java-boy-zh/secret-chat/edit/master/README.md)]

### 3. 编译部署
```bash
# 编译项目
mvn clean package

# 启动服务
# 1. 启动Nacos
# 2. 启动网关服务
# 3. 启动其他微服务
```

## 功能特性

### 核心功能
- 安全的即时通讯
  - 端到端加密
  - 消息防篡改
  - 消息防重放
- 文件传输
  - 支持大文件传输
  - 断点续传
  - 文件加密存储
- 群聊支持
  - 群组管理
  - 群消息同步
  - 群成员管理
- 消息加密
  - 非对称加密
  - 对称加密
  - 密钥管理
- 离线消息
  - 消息持久化
  - 消息同步
  - 消息状态追踪

### 扩展功能
- AI智能助手
  - 智能对话
  - 内容生成
  - 知识问答
- 文档处理
  - 文档预览
  - 文档转换
  - 文档协作
- 系统管理
  - 用户管理
  - 权限控制
  - 系统监控

## 安全特性
- 用户认证与授权
- 数据加密传输
- 敏感信息保护
- 访问控制
- 操作审计

## 性能优化
- 消息队列削峰
- 缓存优化
- 数据库优化
- 网络传输优化
- 并发处理优化

## 开发团队
- 项目维护者：[王青玄]
- 技术支持：[王青玄]
- 贡献者：[王青玄]

## 联系方式
- 项目地址：[项目地址]
- 问题反馈：[问题反馈地址]
- 文档地址：[文档地址]