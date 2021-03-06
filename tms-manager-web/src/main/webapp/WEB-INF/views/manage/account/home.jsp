<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 账户管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/head.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                账户管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form  class="form-inline">
                        <input type="text" name="nameMobile" placeholder="账户或手机号码" class="form-control" value="${param.nameMobile}">
                        <select name="rolesId" class="form-control">
                            <option value="">全部账号</option>
                            <c:forEach items="${rolesList}" var="roles">
                                <option value="${roles.id}" ${param.rolesId == roles.id ? 'selected' : ''}>${roles.rolesName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-info">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <div class="box-tools">
                            <a href="/manage/account/new" class="btn btn-success btn-sm">
                                <i class="fa fa-plus"></i>新增账号
                            </a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>账号</th>
                            <th>手机号码</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${accountList}" var="account">
                            <tr>
                                <td>${account.accountName}</td>
                                <td>${account.accountMobile}</td>
                                <td>
                                    <c:forEach items="${account.rolesList}" var="roles">
                                        ${roles.rolesName}
                                    </c:forEach>
                                </td>
                                <td>${account.accountState}</td>
                                <td>
                                    <fmt:formatDate value="${account.creatTime}"/>
                                </td>
                                <td><a href="/manage/account/${account.id}/edit"><i class="fa fa-edit"></i></a></td>
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
</body>
</html>
