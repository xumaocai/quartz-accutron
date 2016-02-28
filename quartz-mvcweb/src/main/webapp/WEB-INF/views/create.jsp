<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Quartz Web</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>

<body>
<div class="wrapper">

    <div class="content-wrapper">
        <section class="content-header">
            <div class="box" style="margin-bottom: 0px">
                <div class="box-header">
                    <h3 class="box-title">任务组列表</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <form method="post">
                        <label>ID</label>
                        <input type="text" name="scheduleJobId" />
                        <label>任务名称</label>
                        <input type="text" name="jobName" />
                        <label>任务别名</label>
                        <input type="text" name="aliasName" />
                        <label>任务分组</label>
                        <input type="text" name="jobGroup" />
                        <label>触发器ID</label>
                        <input type="text" name="jobTrigger" />
                        <label>任务状态</label>
                        <input type="text" name="status" />
                        <label>时间表达式</label>
                        <input type="text" name="cronExpression" />
                        <label>是否异步</label>
                        <input type="text" name="isSync" />
                        <label>描述</label>
                        <input type="text" name="description" />
                        <input type="submit" value="创建" />

                    </form>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- REQUIRED JS SCRIPTS -->
<script>
</script>
</body>
</html>


