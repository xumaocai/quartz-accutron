# 定时任务 Quartz Accutron

## 概览
每个模块都是一个可以独立运行的项目.

- quartz-xmlweb - [web] 直接从 xml 中读取定时任务数据,通过 web 展示，可以很好的与`1.x`版本兼容
- quartz-mvcweb - [web] 结合 spring，做可视化的任务调度,用数据库做记录
- quartz-xmljob - [server] 没有 web 界面,通过 console 或者日志查看执行情况,兼容 quartz 1.x 版本的代码，可快速切换到 2.x
- quartz-simple - [examples] 简单的基本示例，很多来自官方的解释

### xmlweb
![pt_black](https://raw.github.com/yantze/quartz-accutron/master/other/img/xmlweb1.png)