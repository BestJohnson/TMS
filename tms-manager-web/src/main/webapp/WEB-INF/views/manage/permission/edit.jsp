<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%--导航栏--%>
    <%@include file="../../include/head.jsp"%>

    <!-- =============================================== -->
    <%--左边栏--%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"/>
    </jsp:include>

    <!-- =============================================== -->
    <!-- 右侧部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
                <h1>
                    权限修改
                </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <form method="post" id="saveForm">
                    <div class="form-group">
                        <label>权限名称</label>
                        <input type="text" name="permissionName" class="form-control" value="${permission.permissionName}">
                    </div>
                    <div class="form-group">
                        <label>权限代号</label>
                        <input type="text" name="permissionCode" class="form-control" value="${permission.permissionCode}">
                    </div>
                    <div class="form-group">
                        <label>资源路径</label>
                        <input type="text" name="url" class="form-control" value="${permission.url}">
                    </div>
                    <div class="form-group">
                        <label>权限类型</label>
                        <select name="permissionType"  class="form-control" >
                            <option value="菜单" ${permission.permissionType == '菜单' ? 'selected' : ''}>菜单</option>
                            <option value="按钮" ${permission.permissionType == '按钮' ? 'selected' : ''}>按钮</option>
                        </select>
                    </div>
                </form>
                <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>

<script>
    $(function() {
       $("#saveBtn").click(function () {
           $("#saveForm").submit();
       });


    })
</script>
</body>
</html>
