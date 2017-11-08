<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/upload/emp/${photo}" class="img-circle"
                     alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${name}</p>
            </div>
        </div>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->

        <ul class="sidebar-menu">
            <li class="header"><i class="fa fa-slack"></i> 行政资源管理系统</li>
            <c:forEach items="${groups}" var="group">
                <li class="treeview"><a href="<%=basePath%>pages/index.jsp"> <i
                        class="fa fa-folder-open"></i> <span>${group.title}</span> <i
                        class="fa fa-angle-left pull-right"></i>
                </a>
                    <ul class="treeview-menu">
                        <c:forEach items="${group.actions}" var="act">
                            <c:if test="${act.sflag == 1}">
                                <li><a href="<%=basePath%>${act.url}"><i class="fa fa-circle-o"></i>
                                        ${act.title}</a></li>
                            </c:if>
                        </c:forEach>

                    </ul>
                </li>
            </c:forEach>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>