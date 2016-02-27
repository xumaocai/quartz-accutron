<%--
  Created by IntelliJ IDEA.
  User: issac.yu
  Date: 2016/1/5
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
    <div class="box" style="margin-bottom: 0px">
        <div class="box-header">
            <h3 class="box-title">任务组列表</h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <table id="example" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>任务组名称</th>
                    <th>业务</th>
                    <th>任务数</th>
                    <th>执行中</th>
                    <th>未执行</th>
                    <th>创建者</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</section>
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.function}}">{{this.name}}</button>
    {{/each}}
</script>
<script>
    var table;
    var parentTaskId;
    $(document).ready(function () {
        var dateFormat = function (data) {
            date = new Date(data);

            return [date.getFullYear(), ("0" + (date.getMonth() + 1)).slice(-2), ("0" + date.getDate()).slice(-2)].join('-')
                    + " " + [("0" + date.getHours()).slice(-2), ("0" + date.getMinutes()).slice(-2)].join(':');
        };

        var tpl = $("#tpl").html();
        //预编译模板
        $.extend( $.fn.dataTable.defaults, {
            "searching": false
        } );

        var template = Handlebars.compile(tpl);
       table =  $('#example').DataTable({
           "dom":'<"toolbar">lrt<"bottom"ip>',
            "aaSorting": [0, 'desc'],
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": apiAddress + "/group",
                "dataType": "JSONP",
                "data": function (data) {
                    var searchKey = $('.toolbar').find('.dropdown-toggle').children().eq(0).attr("param");
                    var searchValue  =$('.toolbar').find('input').val();
                    var extraSearch =searchKey+':'+searchValue;
                    data.extra_search = extraSearch;
                    return {requestJson: JSON.stringify(data)}
                }
            },
            "columns": [
                {"data": "parentTaskId", "defaultContent": "<i>Not set</i>"},
                {"data": "name", "defaultContent": "<i>Not set</i>"},
                {"data": "appType", "defaultContent": "<i>Not set</i>"},
                {"data": "taskTotal", "defaultContent": "<i>Not set</i>"},
                {"data": "taskStatusRun", "defaultContent": "<i>Not set</i>"},
                {"data": "taskStatusStop", "defaultContent": "<i>Not set</i>"},
                {"data": "creater", "defaultContent": "<i>Not set</i>"},
                {
                    "data": "createTime",
                    "defaultContent": "<i>Not set</i>",
                    "render": dateFormat
                },


                {
                    "data": "updateTime",
                    "defaultContent": "<i>Not set</i>",
                    "render": dateFormat
                },
                {"data": "remark", "defaultContent": "<i>Not set</i>", sortable: false},
                {
                    "data": null,
                    "defaultContent": "<i>Not set</i>"
                }
            ],
            columnDefs: [
                {
                    "targets": 1,
                    "render": function (data, type, column, row) {
                        return '<a href="${ctx}/group/' + column.parentTaskId + '/task">' + data + '</a>';
                    }
                },
                {
                    "targets": 2,
                    "render": function (data, type, column, row) {
                        var result;
                        if (column.appType==1){
                            result = '金融港'
                        }else if (column.appType==2){
                            result= '滚雪球'
                        }else if (column.appType==3){
                            result ='金斧子'
                        }

                        return result;
                    }
                },
                {
                    targets: 10,
                    sortable: false,
                    render: function (data, type, column, row) {
                        var context = {
                            func: [
                                {'name':'查看','type':'default', 'function': 'showButton(' + column.parentTaskId + ')'},
                                {'name':'编辑','type':'primary', 'function': 'editButton(' + column.parentTaskId + ')'},
                                {'name':'删除','type':'danger', 'function': 'delTask(' + column.parentTaskId + ')'}

                            ]
                        };
                        return template(context);
                    }
                }],
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        //查询
        var dataSelect= {"searchConfig":[{"key":"parentTaskId","value":"id"},{"key":"name","value":"任务组名称"},{"key":"creater","value":"业务"}
            ,{"key":"creater","value":"创建者"},{"key":"createTime","value":"创建时间"},{"key":"updateTime","value":"更新时间"}]};
        $("div.toolbar").html(searchFunction(dataSelect));
    });
    var editButton = function (parentTaskId) {
        window.location.href = "${ctx}/group/" + parentTaskId + "/edit";
    };
    var showButton = function(parentTaskId,data){
        window.location.href = "${ctx}/group/" + parentTaskId + "/task/";

    }
    var delTask = function(id){
            modalTips();
            parentTaskId=id;
            $('body').append(simpleTips('删除提示！','你确认要进行删除吗？删除后数据将无法恢复'));
            $('#myModal').modal('show');
    }

    $(function(){
        searchClickFunction(table);
    })
    $(document).ready(function(){
        $(document).on('click','#confirm_click',function(){
//            var parentTaskId =$('#example').find('tr').eq(1).children('td').eq(0).text();
            $('#myModal').modal('hide');
            $.post( "${ctx}/group/" + parentTaskId + "/delete");
            table.ajax.reload()
            $('#simpleTips').remove();
            $('#myModal').remove();
            $('.modal-backdrop').remove();
//            modalClear();
        })
        $(document).on('click','#close_click',function() {
//            modalClear();
        })

    })
    var modalClear =function(){
        $('#simpleTips').remove();
        $('#myModal').remove();
        $('.modal-backdrop').remove();

    }
</script>