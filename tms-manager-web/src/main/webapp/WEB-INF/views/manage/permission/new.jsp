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
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->
    <!-- 右侧部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                权限管理
            </h1>
        </section>

        <!-- Main content -->
        <div class="row">
            <div class="col-md-9 ">
                <section class="content ">
                    <div class="box">
                        <div class="box-head">
                            <h3 class="box-title">新增权限</h3>
                            <div class="box-tools">
                                <a href="/manage/permission" class="btn btn-success pull-right ">返回</a>
                            </div>
                        </div>
                        <div class="box-body">
                            <form method="post" id="saveForm">
                                <div class="form-group">
                                    <label>权限名称</label>
                                    <input type="text" name="permissionName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>权限代号</label>
                                    <input type="text" name="permissionCode" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>资源路径</label>
                                    <input type="text" name="url" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>权限类型</label>
                                    <select name="permissionType" class="form-control">
                                        <option value="菜单">菜单</option>
                                        <option value="按钮">按钮</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>父权限</label>
                                    <select name="parentId"  class="form-control">
                                        <option value="0">顶级菜单</option>
                                        <c:forEach var="permission" items="${permissionList}">
                                            <option value="${permission.id}">${permission.permissionName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </form>
                        </div>
                        <div class="box-footer">
                            <button class="btn pull-right btn-success" id="saveBtn">保存</button>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    })
</script>
</body>
</html>
