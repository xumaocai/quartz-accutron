<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: issac.yu
  Date: 2016/1/5
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>ULC | Create Task</title>
</head>
<section class="content-header">
    <div class="box" style="margin-bottom: 0px">
        <div class="box-header">
            <h3 class="box-title">新建任务组</h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <div class="raw">
                <div class="col-md-8">
                    <form id="new_group_form" action="${ctx}/group/create" method="POST" style="padding: 20px;">
                        <div>
                        <label>任务组名称：</label>
                        <input id="groupName" class="form-control" name="name" type="text" placeholder="输入任务名称"/>
                        </div>
                        <div>
                        <label>业务类型：</label>
                        <select name="appType" class="form-control">
                            <option value="1">滚雪球</option>
                            <option value="2">金斧子</option>
                            <option value="3">金融港</option>
                        </select>
                        </div>
                        <div>
                        <label>任务组备注：</label>
                        <textarea class="form-control" name="remark" placeholder="输入任务备注"></textarea>
                        </div>
                        <input type="submit" class="save_parent_task_btn btn btn-default" value="保存"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function clearPreviousValue(){
        if($(".remote").data("previousValue")){
            $(".remote").data("previousValue").old = null;
        }
    };
    $("#groupName").change(function(){
        clearPreviousValue();
    });
</script>
<%--<script type="application/javascript" src="${ctx}/static/js/task.js" ></script>--%>
</html>
