<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include page="/pages/plugins/include_javascript.jsp"/>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <script type="text/javascript" src="../jquery/jquery.backstretch.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>

</head>
<body>
<div class="top-content">
    <div class="inner-bgx">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>企业人事管理系统</h3>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"><img src="../images/login-lock.png"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="background: white;">
                        <div class="h5">程序出错了，请返回<a class="text-danger" href="/login.jsp">首页</a>，与管理员联系！</div>
                        <div class="h5">
                            <span class="text-danger">上传文件不能超过5M！</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
