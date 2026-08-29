# moosttar-ddd-scaff

基于 DDD（Domain-Driven Design）分层架构的 Java 后端 Maven Archetype 脚手架，开箱即用。一条命令即可生成整套多模块项目。

已发布到 **Maven Central**（`io.github.moosttar`），**免认证、开箱即用**。

## 使用 Archetype 生成项目

```bash
mvn archetype:generate \
  -DarchetypeGroupId=io.github.moosttar \
  -DarchetypeArtifactId=moosttar-ddd-scaff \
  -DarchetypeVersion=1.1 \
  -DgroupId=你的group \
  -DartifactId=你的项目名 \
  -Dpackage=你的包名
```

交互式生成同样支持（跳过 `-DgroupId` 等参数，Maven 会提示输入）。

### 在 IDEA 中使用（无需任何认证配置）

1. 菜单 **File → New → Project**，左侧选 **Maven**，勾选 **Create from archetype**；
2. 点 **Add Archetype…**，填入 `io.github.moosttar:moosttar-ddd-scaff:1.1`，确定后即可在列表中选择；
3. 或在 **Manage Archetype Catalogs** 中添加 Maven Central 目录 `https://repo1.maven.org/maven2/archetype-catalog.xml`，然后从列表里搜索 `moosttar-ddd-scaff`。

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

推 tag 自动发布到 Maven Central（见 `.github/workflows/publish.yml`）：

```bash
git tag v1.1
git push origin v1.1
```

发布前需在 GitHub 仓库配置 secrets：

| Secret | 来源 |
| ---- | ---- |
| `CENTRAL_USERNAME` / `CENTRAL_PASSWORD` | central.sonatype.com → 头像 → **View Account** → **User Token** |
| `GPG_PRIVATE_KEY` / `GPG_PASSPHRASE` | 本机生成：`gpg --gen-key`；导出：`gpg --export-secret-key --armor <keyid>`（私钥含口令则为 `GPG_PASSPHRASE`） |

手动发布：`~/.m2/settings.xml` 配置 `<server><id>central</id></server>`（username/password 用 User Token），本地 `gpg` 密钥就绪后执行 `mvn clean deploy`，再到 Central Portal → Deployments 点 **Publish**。

## 本地验证

```bash
# 构建 archetype 并安装到本地仓库（跳过签名，快速验证结构）
mvn clean install -Dgpg.skip=true

# 端到端集成测试：生成示例项目并 mvn package
mvn archetype:integration-test
```

## License

Apache License 2.0
