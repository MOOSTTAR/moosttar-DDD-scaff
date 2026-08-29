#set($HASH = "#")
# ${artifactId}

基于 DDD（Domain-Driven Design）分层架构的 Java 后端脚手架，开箱即用。作为 Maven Archetype 发布，一条命令即可生成整套项目。

$HASH$HASH 模块结构

```text
├── ${artifactId}-app            # 应用层：启动入口、配置、Web 层组装
├── ${artifactId}-trigger        # 触发层：HTTP / RPC / Job / MQ 入口
├── ${artifactId}-api            # 对外接口层：RPC 接口定义与模型
├── ${artifactId}-domain         # 领域层：业务模型（entity / valobj / aggregate / repository / service）
├── ${artifactId}-infrastructure # 基础设施层：持久化、缓存、网关
└── ${artifactId}-types          # 公共类型层：通用返回、异常、常量、工具
```

$HASH$HASH 技术栈

| 组件 | 说明 |
| ---- | ---- |
| JDK 1.8 / Spring Boot 2.7.12 | 基础框架 |
| MyBatis + ShardingSphere-JDBC | 数据库访问、分库分表 |
| MySQL 8.0 | 数据库 |
| Redis + Redisson | 缓存 |
| Dubbo + Nacos | RPC 与注册/配置中心 |
| RocketMQ | 消息队列 |
| XXL-Job | 分布式任务调度 |

$HASH$HASH 快速开始

$HASH$HASH$HASH 环境依赖

本地需准备：JDK 1.8+、Maven 3.6+，以及以下中间件（可用 `docs/dev-ops/environment/docker-compose.yml` 一键拉起）：

```bash
docker-compose -f docs/dev-ops/environment/docker-compose.yml up -d
```

> 注意：脚手架未内置 MySQL / Redis 的网页管理界面（phpmyadmin、redis-commander 已移除），数据库查看请使用 Navicat、DBeaver 等客户端工具。中间件账号密码见各配置文件的默认值。

启动后访问：
- Nacos 控制台：http://127.0.0.1:8848/nacos 【nacos / nacos】
- XXL-Job 调度中心：http://127.0.0.1:9090/xxl-job-admin 【admin / 123456】
- RocketMQ 控制台：http://localhost:8080 【admin / admin】

$HASH$HASH$HASH 启动应用

```bash
mvn clean package
# 进入启动模块
cd ${artifactId}-app
mvn spring-boot:run
```

默认端口 `8091`，测试接口：`GET http://localhost:8091/success`

$HASH$HASH 使用 Archetype 生成新项目

```bash
mvn archetype:generate \
  -DarchetypeGroupId=cn.moosttar \
  -DarchetypeArtifactId=moosttar-ddd-scaff \
  -DarchetypeVersion=1.1 \
  -DgroupId=你的group \
  -DartifactId=你的项目名 \
  -Dpackage=你的包名
```

> GitHub Packages 下载需要认证，首次使用请先按《发布与使用》文档配置 `~/.m2/settings.xml`。

$HASH$HASH License

Apache License 2.0
