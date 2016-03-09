xmlweb Module
---
通过 xml 存储 Schedule Job，并且把任务计划显示在 web 页面。

## 1 使用说明
一般情况下，任务计划简单，可以考虑使用 xmlweb Module。
如果是一个复杂的新项目，可以考虑使用 [mvcweb module](https://github.com/yantze/quartz-accutron/tree/master/quartz-mvcweb).

### 1.1 运行起来
在命令行里面执行
```
mvn clean
tomcat7:run-war
```

## 2 xmlweb 兼容版升级指南
> 如果你以前使用的是 quartz1.x, 并且没有使用 web 界面，可以通过下面的步骤，添加升级 quartz 到最新版本，并且可以使用 web 实时查看任务运行状态。


##### 2.1 升级 quartz
```
<!--quartz-->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>${quartz.version}</version>
</dependency>
<!--quartz jobs package has been separated into its own jar starting 2.2.0 for security purpose.-->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz-jobs</artifactId>
    <version>${quartz.version}</version>
</dependency>
<!-- Transaction dependency is required with Quartz integration -->
<dependency>
    <groupId>javax.transaction</groupId>
    <artifactId>jta</artifactId>
    <version>1.1</version>
</dependency>

```

##### 2.2 添加 spring mvc 库
```
<!--web-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.0.1</version>
    <scope>provided</scope>
</dependency>
```

##### 2.3 升级任务定义

对照着新版本的 spring-quartz.xml，对原来的版本做迁移。

##### 2.4 拷贝相关 web 代码
把五个文件夹 common、controller、entity、service、task 转移到你的项目中去.

```
common : 任务操作工具类、任务状态类
controller: wen 端访问路由控制
entity: 每个任务的类定义
service: 调用工具类操作任务
task: 任务
```
##### 2.5 拷贝 `spring-quartz.xml` 到 `resources` 中

##### 2.6 拷贝 `webapp` 到新项目中,与 `resources` 同级

##### 2.7 配置 webapp 文件夹的属性
打开`Project Structure`,在左侧的 `Project Settings` 选择 `Facets`,添加一个 web属性,如下图
![pt_black](https://raw.github.com/yantze/quartz-accutron/master/other/img/web_config.png)

下面这两个路径一定要设置的:
```
/Users/yantze/code/web/javaweb/quartz-accutron/quartz-xmlweb/src/main/webapp/WEB-INF/web.xml
/Users/yantze/code/web/javaweb/quartz-accutron/quartz-xmlweb/src/main/webapp
```

##### 2.8 配置 tomcat 属性就可以运行啦.


## 3 Question

- List<? extends Trigger> 这个不好判断