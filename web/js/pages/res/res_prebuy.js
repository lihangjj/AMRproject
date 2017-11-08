$(function() {
	$("[id*=res-]").each(function () {
		$("#applyDiv").show() ;
	}) ;
	$("#allPrice").text(calSum()) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('did',this.checked) ;
	}) ;
	// 实现整体修改操作的功能
	$(editBtn).on("click",function(){
		// 定义一个数组，保存所有需要被删除的gid数据
		var del = "" ;
		var foot = 0 ;
		var data = "" ; // 实现最终数据拼凑的字符串
		$("[id*=amount-]").each(function(){
			var gid = this.id.split("-")[1] ;
			var amount = this.value ;
			if (amount != "0") {
				data += gid + ":" + amount + "|" ;
			} else {
				del += gid + "|" ;
			}
		}) ; 
		if (data != "") {
			// 进行ajax异步数据处理操作
			$.post("pages/res/editAmount.action",{updatestr:data,deletestr:del},function(data){
				operateAlert(data.trim() == "true","商品数量修改成功！","商品数量修改失败！") ;
				var temp = del.split("|") ;
				for (var x = 0 ; x < temp.length ; x ++) {
					$("#res-" + temp[x]).remove() ;
				}
				$("#allPrice").text(calSum()) ;
			},"text") ;
		} else {
			operateAlert(false,"商品数量修改成功！","商品数量修改失败！") ;
		}
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		var del = "" ;
		$(":checked").each(function() {
			if(this.id == "did") {	// 要删除的内容
				del += this.value + "|" ;
			}
		}) ;
		if (del != "") {
			$.post("pages/res/rm.action",{deletestr:del},function(data){
				operateAlert(data.trim() == "true","商品信息删除成功！","商品信息删除失败！") ; 
				var temp = del.split("|") ;
				for (var x = 0 ; x < temp.length ; x ++) {
					$("#res-" + temp[x]).remove() ;
				} 
				$("#allPrice").text(calSum()) ;
			},"text") ;
		} else {
			operateAlert(false,"商品信息删除成功！","商品信息删除失败！") ;
		}
	}) ;
	$("button[id*=add-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$("#amount-" + gid).val(amount+1) ;
			$("#allPrice").text(calSum()) ;
		})
	}) ;
	$("button[id*=sub-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (amount > 0) {
				$("#amount-" + gid).val(amount - 1) ;
				$("#allPrice").text(calSum()) ;
			}
		})
	}) ;
})
function calSum() {
	var sumPrice = 0.0 ;	// 保存总价
	// 计算购买的商品的总价数据
	$("span[id*=price-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		var price = $(this).text() ;	// 取得文本内容
		var amount = $("#amount-" + gid).val() ;	// 直接取得value属性
		sumPrice += parseFloat(price) * parseInt(amount) ;
	}) ;
	return round(sumPrice,2) ;
}