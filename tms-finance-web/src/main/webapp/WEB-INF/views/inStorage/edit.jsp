<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票入库</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/head.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="ticket_storage"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票入库修改
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label >入库时间</label>
                            <input type="text" class="form-control" name="createTime" value="<fmt:formatDate value="${ticketInRecord.createTime}" timeStyle="YYYY-MM-dd"/>">
                        </div>
                        <div class="form-group">
                            <label>内容</label>
                            <input type="text" class="form-control" name="content" value="${ticketInRecord.content}">
                        </div>
                        <div class="form-group">
                            <label>起始票号</label>
                            <input type="text" class="form-control" name="beginTicketNum" value="${ticketInRecord.beginTicketNum}">
                        </div>
                        <div class="form-group">
                            <label>截至票号</label>
                            <input type="text" class="form-control" name="endTicketNum" value="${ticketInRecord.endTicketNum}">
                        </div>
                        <div class="form-group">
                            <label>数量</label>
                            <input type="text" class="form-control" name="totalNum" value="${ticketInRecord.totalNum}">
                        </div>
                        <div class="form-group">
                            <label>入库人</label>
                            <input type="text" class="form-control" name="accountName" value="${ticketInRecord.accountName}">
                        </div>
                        <div class="form-group">
                            <label>入库人id</label>
                            <input type="text" class="form-control" name="accountId" value="${ticketInRecord.accountId}">
                        </div>
                    </form>
                </div>
                <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        })


    })

</script>
</body>
</html>
