## 开始使用

### 启动项目

1. 创建 test 数据库，并导入 resources/sql/table-schema.sql

2. 配置 tomcat 服务器启动即可



### 升级步骤 1.x -> 2.x

1. 升级 quartz 到最新版本
```
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.2.2</version>
</dependency>
```

2. 然后把 Task 放到项目中的 task 目录

3. 在 spring-quartz.xml 中添加对应的 task 类 和定义 task 的名称




## Rules

### Inspect code
- shift + alt + i - check useless code


## Problem

### 已知问题

- 目前不支持 mybatis-spring version > 1.2.3 的版本， 原因待查
```
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.2.3</version>
</dependency>
```

- context 出现三次， 原因待查
Looking for @ControllerAdvice: WebApplicationContext for namespace 'springServlet-servlet'

## Thinks

- [Spring 3整合Quartz 2实现定时任务](http://www.dexcoder.com/selfly/article/308)
- lijing




