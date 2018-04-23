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
        <jsp:param name="menu" value="ticket_out_storage"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票下发
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">入库列表</h3>
                    <div class="box-tools">
                        <a href="/ticket/out/storage/new" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> 新增下发</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>下发售票点</th>
                            <th>起始票号</th>
                            <th>截至票号</th>
                            <th>下发时间</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>总金额</th>
                            <th>缴费情况</th>
                            <th>下发人</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty page.list}">
                            <td>暂无下发记录</td>
                        </c:if>
                        <c:forEach items="${page.list}" var="ticketOutRecord">
                            <tr>
                                <td>${ticketOutRecord.ticketStore}</td>
                                <td>${ticketOutRecord.ticketBeginNum}</td>
                                <td>${ticketOutRecord.ticketEndNum}</td>
                                <td><fmt:formatDate value="${ticketOutRecord.outTime}" timeStyle="YYYY-MM-dd"/> </td>
                                <td>${ticketOutRecord.price}</td>
                                <td>${ticketOutRecord.totalNum}</td>
                                <td>${ticketOutRecord.money}</td>
                                <td>
                                    <span class="label ${ticketOutRecord.state == '已支付' ? 'label-success' : 'label-danger'}">${ticketOutRecord.state}</span>
                                </td>
                                <td>${ticketOutRecord.accountName}</td>
                                <td>
                                    <c:if test="${ticketOutRecord.state} == '未支付'">
                                        <a class="btn btn-danger btn-xs delLink" rel="${ticketOutRecord.id}" href="javascript:;" title="删除"><i class="fa fa-trash"></i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${pageInfo.pages > 1}">
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/page/jquery.twbsPagination.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {

        $("#pagination-demo").twbsPagination({
           totalPages : ${page.pages},
           visiblePages : 5,
            first : '首页',
            last :  '尾页',
            prev : '上一页',
            next : '下一页',
            href : '?p={{number}}'


        });


        $(".delLink").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function (index) {
                layer.close(index);
                $.get("/ticket/out/storage" + id + "/del").done(function (result) {
                    if(result.status == 'success') {
                        window.history.go(0);
                    } else {
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("系统异常")
                });
            })
        });




    })

</script>
</body>
</html>
