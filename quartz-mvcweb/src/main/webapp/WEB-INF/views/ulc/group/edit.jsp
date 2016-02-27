<%--
  Created by IntelliJ IDEA.
  User: issac.yu
  Date: 2016/1/5
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>ULC | Edit Task</title>
</head>
</html>

<section class="content-header">
    <div class="box" style="margin-bottom: 0px">
        <div class="box-header">
            <div class="box-header">
                <h3 class="box-title"><a href="${ctx}/group/${group.parentTaskId}/edit">修改任务组信息</a></h3>
            </div><!-- /.box-header -->
        </div><!-- /.box-header -->
        <div class="box-body">
            <div class="raw">
                <div class="col-md-8">
                    <form id="update_parent_task_Form"  action="${ctx}/group/${groupId}/update" method="POST"  style="padding: 20px;">
                        <label>任务名称：</label>
                        <input class="form-control" name="name" type="text" placeholder="输入任务名称" value="${group.name}"/>
                        <label>业务类型：</label>
                        <select name="appType" class="form-control">
                            <option value="1" <c:if test="${group.appType == 1}">selected="selected"</c:if> >金融港</option>
                            <option value="2" <c:if test="${group.appType == 2}">selected="selected"</c:if> >滚雪球</option>
                            <option value="3" <c:if test="${group.appType == 3}">selected="selected"</c:if> >金斧子</option>
                        </select>
                        <label>任务备注：</label>
                        <textarea class="form-control" name="remark" placeholder="输入任务备注">${group.remark}</textarea>
                        <div class="tasks_div">
                            <%--<tags:task_form parentTags="${parentTags}" gxqCouponDicts="${gxqCouponDicts}"/>--%>
                        </div>
                        <input type="submit" class="btn btn-default" value="更新"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    var ctx = '${ctx}';
</script>
