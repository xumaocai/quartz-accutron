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
                    <h3 class="box-title">创建任务</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <form method="post">
                        <table>
                            <tr>
                                <td>任务名称</td>
                                <td><input  type="text" name="jobName"></td>
                            </tr>
                            <tr>
                                <td>任务分组</td>
                                <td><input  type="text" name="jobGroup"></td>
                            </tr>
                            <tr>
                                <td>任务别名</td>
                                <td><input type="text" name="aliasName"></td>
                            </tr>
                            <tr>
                                <td>触发器名称</td>
                                <td><input  type="text" name="jobTrigger"></td>
                            </tr>
                            <tr>
                                <td>时间表达式</td>
                                <td><input type="text" name="cronExpression"></td>
                            </tr>
                            <tr>
                                <td>是否异步</td>
                                <td><input type="text" name="isSync"></td>
                            </tr>
                            <tr>
                                <td>状态</td>
                                <td><input type="text" name="status"></td>
                            </tr>
                            <tr>
                                <td>任务描述</td>
                                <td><input type="text" name="description"></td>
                            </tr>
                        </table>
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


