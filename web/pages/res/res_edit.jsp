<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath + "pages/res/edit.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript_head.jsp" /> 
<script type="text/javascript" src="/js/pages/res/res_edit.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp" />
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><strong>编辑待购用品</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="">
							<form class="form-horizontal" action="<%=editUrl%>" id="myform" method="post" enctype="multipart/form-data">
								<fieldset>
									<div class="form-group" id="titleDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="title">用品名称：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="title" name="title" class="form-control"
												placeholder="请输入要购买的用品名称" value="${details.title}">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="titleMsg"></div>
									</div>
									<div class="form-group" id="type.tidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="type.tid">用品分类：</label>
										<div class="col-md-5">
											<select id="type.tid" name="tid" class="form-control">
												<c:forEach items="${allType}" var="type">
													<option value="${type.tid}" ${details.type.tid==type.tid?"selected":""}>${type.title}</option>
												</c:forEach>
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="type.tidMsg"></div>
									</div>
									<div class="form-group" id="subtype.stidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="subtype.stid">用品子类：</label>
										<div class="col-md-5">
											<select id="subtype.stid" name="stid" class="form-control">
												<c:forEach items="${allSubtype}" var="subtype">
													<option value="${subtype.stid}" ${details.subtype.stid==subtype.stid?"selected":""}>${subtype.title}</option>
												</c:forEach>
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="subtype.stidMsg"></div>
									</div>
									<div class="form-group" id="priceDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="price">用品单价：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="price" name="price" class="form-control"
												placeholder="请输入商品购买单价" value="${details.price}">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="priceMsg"></div>
									</div>
									<div class="form-group" id="rflagDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="rflag">是否重复使用：</label>
										<div class="col-md-5">
											<div class="radio-inline">
												<label><input type="radio" name="rflag" id="rflag" value="1" ${details.rflag == 1 ? "checked" : ""}>重复使用</label>
											</div>
											<div class="radio-inline">
												<label><input type="radio" name="rflag" id="rflag" value="0" ${details.rflag == 0 ? "checked" : ""}>不重复使用</label>
											</div>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="rflagMsg"></div>
									</div>
									<div class="form-group" id="picDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="pic">用品图片：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="file" id="pic" name="pic" class="form-control"
												placeholder="如果修改图片可以上传">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="picMsg"></div> 
									</div>
									<div class="form-group">
										<div class="col-md-5 col-md-offset-3">
											<input type="hidden" name="amount" value="${details.amount}">
											<input type="hidden" id="oldTitle"  value="${details.title}">
											<input type="hidden" name="did" value="${details.did}">
											<input type="hidden" name="photo" value="${details.photo}">
											<button type="submit" class="btn btn-primary">更新</button>
											<button type="reset" class="btn btn-warning">重置</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/include_javascript_foot.jsp" />
</body>
</html>
