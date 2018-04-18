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
                角色管理
            </h1>
        </section>

        <!-- Main content -->
                <section class="content ">
                    <div class="box">
                        <div class="box-head">
                            <h3 class="box-title">新增角色</h3>
                            <div class="box-tools">
                                <a href="/manage/roles" class="btn btn-success pull-right ">返回</a>
                            </div>
                        </div>
                        <div class="box-body">
                            <form method="post" id="saveForm">
                                <div class="form-group">
                                    <label>角色名称</label>
                                    <input type="text" name="rolesName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>角色代号</label>
                                    <input type="text" name="rolesCode" class="form-control">
                                </div>

                                <table class="table tree">
                                    <thead>
                                    <tr>
                                        <th>权限名称</th>
                                        <th>权限代号</th>
                                        <th>资源路径</th>
                                        <th>类型</th>
                                        <th>备注</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${permissionList}" var="permission">
                                        <c:choose>
                                            <c:when test="${permission.parentId == 0}">
                                                <tr class="treegrid-${permission.id} treegrid-expanded">
                                                    <th>
                                                        <input type="checkbox" name="permissionId" value="${permission.id}">
                                                    </th>
                                                    <td>${permission.permissionName}</td>
                                                    <td>${permission.permissionCode}</td>
                                                    <td>${permission.url}</td>
                                                    <td>${permission.permissionType}</td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="treegrid-${permission.id} treegrid-expanded treegrid-parent-${permission.parentId}">
                                                    <th>
                                                        <input type="checkbox" name="permissionId"  value="${permission.id}">
                                                    </th>
                                                    <td>${permission.permissionName}</td>
                                                    <td>${permission.permissionCode}</td>
                                                    <td>${permission.url}</td>
                                                    <td>${permission.permissionType}</td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div class="box-footer">
                            <button class="btn pull-right btn-success" id="saveBtn">保存</button>
                        </div>
                    </div>
                </section>


        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>

<script>
    $(function () {
        $('.tree').treegrid();
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    })
</script>
</body>
</html>
