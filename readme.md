# 定时任务 Quartz Accutron

## 概览

- quartz-xmlweb - 直接从 xml 中读取定时任务数据,通过 web 展示，可以很好的与`1.x`版本兼容
- quartz-mvcweb - 结合 spring，做可视化的任务调度,用数据库做记录
- quartz-xmljob - 兼容 quartz 1.x 版本的代码，可快速切换到 2.x
- quartz-simple - 简单的基本示例，很多来自官方的解释

### xmlweb
![pt_black](https://raw.github.com/yantze/quartz-accutron/master/other/img/xmlweb1.png)

## 使用 `git flow` 开发

[基本教程](http://danielkummer.github.io/git-flow-cheatsheet/)

- feature
    - Start a new feature
        Development of new features starting from the 'develop' branch.

    - Finish up a feature
        Finish the development of a feature. This action performs the following
            - Merged MYFEATURE into 'develop'
            - Removes the feature branch
            - Switches back to 'develop' branch
