# moosttar-DDD-scaff

基于 DDD（Domain-Driven Design）分层架构的 Java 后端 Maven Archetype 脚手架，开箱即用。一条命令即可生成整套多模块项目。

## 使用 Archetype 生成项目

> GitHub Packages 下载**需要认证**（即使是公开包），首次使用请先完成下方《配置认证》小节。

```bash
mvn archetype:generate \
  -DarchetypeGroupId=cn.moosttar \
  -DarchetypeArtifactId=moosttar-DDD-scaff \
  -DarchetypeVersion=1.1 \
  -DgroupId=你的group \
  -DartifactId=你的项目名 \
  -Dpackage=你的包名
```

交互式生成同样支持（跳过 `-DgroupId` 等参数，Maven 会提示输入）。

### 配置认证

在 `~/.m2/settings.xml` 中添加：

```xml
<settings>
  <servers>
    <server>
      <id>github-moosttar</id>
      <username>你的GitHub用户名</username>
      <password>你的PAT</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>moosttar-github</id>
      <repositories>
        <repository>
          <id>github-moosttar</id>
          <url>https://maven.pkg.github.com/moosttar/moosttar-DDD-scaff</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>github-moosttar</id>
          <url>https://maven.pkg.github.com/moosttar/moosttar-DDD-scaff</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>moosttar-github</activeProfile>
  </activeProfiles>
</settings>
```

- PAT 需授予 **`read:packages`** 权限（生成项目只需读取）；发布者另需 **`write:packages`**。
- 生成流程会先把 archetype 插件本身拉到本地仓库，之后的构建不会重复下载。

## 生成的工程结构

```text
├── 你的项目-app            # 应用层：启动入口、配置、Web 层组装
├── 你的项目-trigger        # 触发层：HTTP / RPC / Job / MQ 入口
├── 你的项目-api            # 对外接口层：RPC 接口定义与模型
├── 你的项目-domain         # 领域层：业务模型（entity / valobj / aggregate / repository / service）
├── 你的项目-infrastructure # 基础设施层：持久化、缓存、网关
└── 你的项目-types          # 公共类型层：通用返回、异常、常量、工具
```

## 技术栈

| 组件 | 说明 |
| ---- | ---- |
| JDK 1.8 / Spring Boot 2.7.12 | 基础框架 |
| MyBatis + ShardingSphere-JDBC | 数据库访问、分库分表 |
| MySQL 8.0 | 数据库 |
| Redis + Redisson | 缓存 |
| Dubbo + Nacos | RPC 与注册/配置中心 |
| RocketMQ | 消息队列 |
| XXL-Job | 分布式任务调度 |

## 发布（维护者）

推 tag 自动发布到 GitHub Packages（见 `.github/workflows/publish.yml`）：

```bash
git tag v1.1
git push origin v1.1
```

手动发布：在 `~/.m2/settings.xml` 配置 `<server><id>github</id>`（PAT `write:packages`）后执行：

```bash
mvn clean deploy
```

## 本地验证

```bash
# 构建 archetype 并安装到本地仓库
mvn clean install

# 端到端集成测试：生成示例项目并 mvn package
mvn archetype:integration-test
```

## License

Apache License 2.0
