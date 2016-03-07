## 开始使用

### 启动项目

1. 创建 `test` 数据库，并导入 `resources/sql/table-schema.sql`

2. 配置 tomcat 服务器启动即可



### 升级步骤 1.x -> 2.x

1. 升级 quartz 到最新版本

2. 然后把 Task 放到项目中的 task 目录

3. 在 `spring-quartz.xml` 中添加对应的 task 类 且定义 task 的名称


### 新版优点
- 对 job 更多的属性修饰,比如 添加 description.
- 支持 对 job 直接同时运行是否注解
- 实现 job 接口,可以在任务执行时,获取 JobExecutionContext
- 通过 JobExecutionContext 可以实现通用任务
- 通过 JobExecutionContext 可以获取 datamap,获取其它的任务执行情况



### 边界条件分析
quartz 里面有几个关键参数：
- Concurrent - 允许同一个任务同时运行。annotation tells Quartz not to execute multiple instances of a given job definition (that refers to the given job class) concurrently.
- Sync - 同 Concurrent 是一个概念，任务同时运行。
- Timeout - 在 Job 延时多少时，停止执行前面的任务。
- Durability - 如果没有触发器（trigger)，那么这个Job 会被删除。if a job is non-durable, it is automatically deleted from the scheduler once there are no longer any active triggers associated with it. In other words, non-durable jobs have a life span bounded by the existence of its triggers.
- Volatility - 调用函数 `shutdown(true)`，可以让定时任务完成所有当前正在运行的任务后，关闭。

#### 宕机恢复
```
Job Recoverability
When the Scheduler experiences an unexpected shutdown and a job is executing, a recoverable job is re-executed when the Scheduler is restarted. The job starts executing again right from the beginning. The Scheduler has no way of knowing where the job was during execution when the program was stopped and, therefore, must start all over again.
To set a job to be recoverable, use the following method:
public void setRequestsRecovery(boolean shouldRecover);

http://stackoverflow.com/questions/19267263/quartz-jobdetail-requestrecovery
http://stackoverflow.com/questions/14222909/quartz-recovering-from-multiple-days-misfire
```

#### 死锁检测




## Rules

### Inspect code
- shift + alt + i - check useless code

### Mybatis Generator
```
// 在命令行中运行下面的命令可以生成 mapper 和 entity
mvn mybatis-generator:generate

// 之后会在 项目中生成 generator 文件夹，里面包含了生成的文件

// 详细的使用可参考：
// http://git.oschina.net/free/Mapper
```



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

## Thanks

- [Spring 3整合Quartz 2实现定时任务](http://www.dexcoder.com/selfly/article/308)
- lijing




