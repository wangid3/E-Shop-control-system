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
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">筛选商品</strong><small></small></div>
    </div>
    <hr>

    <div class="edit_content">
        <form action="${ctx }/GoodsServlet?action=select&currentPage=1" method="post" id="select_form" style="background: none; width: 700px;">
            <div class="item1">
                <div>
                    <span>商品名称：</span>
                    <input type="text" class="am-form-field" name="name">&nbsp;&nbsp;
                </div>

            </div>

            <div class="item1">
                <div>
                    <span>所属分类：</span>
                    <select id="category_select" name="cid">
                        <c:forEach items="${allCategory }" var="category">
                            <option value="${category.cid }">${category.cname }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="am-btn am-btn-default" type="button" id="select">选择</button>&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset">重置</button>
        </form>
    </div>
</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>

<script>

    $("#select").click(function () {
        //让表单提交 GoodsAddServlet
        //获取表单  让其提交
        $("#select_form").submit();
    });

</script>
</body>
</html>