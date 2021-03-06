<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 角色管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/head.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_roles"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <shiro:hasRole name="admin || superadmin">
                <h1>
                    角色管理
                </h1>
            </shiro:hasRole>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">角色列表</h3>
                    <div class="box-tools">
                            <a href="/manage/roles/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增角色</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <tbody>
                            <c:forEach items="${rolesList}" var="roles">
                                <tr class="bg-gray-active">
                                    <td>
                                        角色名称：<strong>${roles.rolesName}</strong>
                                       <%-- <span class="pull-right">
                                            <a style="color: #fff;" href="/manage/roles/${roles.id}/edit"><i class="fa fa-pencil"></i></a>
                                            <a style="color: #fff;" class="delLink" rel="${roles.id}" href="javascript:;"><i class="fa fa-trash"></i></a>
                                        </span>--%>
                                            <a class="btn btn-danger btn-xs pull-right delLink" rel="${roles.id}" href="javascript:;"><i class="fa fa-trash"></i></a>
                                            <a class="btn btn-primary btn-xs pull-right " href="/manage/roles/${roles.id}/edit"><i class="fa fa-pencil"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <c:forEach items="${roles.permissionList}" var="permission">
                                            <i class="fa fa-circle"></i>${permission.permissionName}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
    });
</script>
</body>
</html>
