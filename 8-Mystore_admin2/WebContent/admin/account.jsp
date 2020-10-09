<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css" />
    <link rel="stylesheet" href="${ctx }/admin/js/pageStyle.css">
    <script src="${ctx }/admin/js/jquery.min.js"></script>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>管理名称</li>
        <li>修改管理</li>
        <li>删除管理</li>
    </ul>

    <c:forEach items="${allAdmins }" var="admins" varStatus="status">
        <ul class="list_goods_ul">
            <li>${status.index + 1}</li>
            <li>${admins.username}</li>
            <li><a href="#"><img class="img_icon" src="${ctx }/admin/images/edit_icon.png" alt=""></a></li>
            <li><a href="#"><img class="img_icon" src="${ctx }/admin/images/delete_icon.png" alt=""></a></li>
        </ul>
    </c:forEach>

</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="${ctx }/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <form action="${ctx }/AdminServletPro?action=addAdmin" method="post" id="add_form" style="background: none; width: 700px;">
        <div class="item1">
            <div>
                <span>管理名称：</span>
                <input type="text" class="am-form-field" name="username">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>账户密码：</span>
                <input type="text" class="am-form-field" name="password" >&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="add2">添加</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset">重置</button>
        </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
        $('#add2').click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
            $("#add_form").submit();
        });

    });
</script>
</body>
</html>