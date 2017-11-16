$(function(){
    $("[id*=editBtn-]").each(function(){
		var did = this.id.split("-")[1] ;
		$(this).on("click",function(){

            // console.log("***** did = " + did) ;
			var title = $("#title-" + did).val() ;
			if (title != "") {	// 表示有数据可以修改
				$.post("pages/dept/edit",{did:did,title:title},function(data){
					operateAlert(data.trim() == "true" , "部门名称修改成功！","部门名称修改失败！") ;
				},"text") ;
			} else {
				operateAlert(true , "部门名称修改成功！","部门名称修改失败！") ;
			}
		}) ;
	})
});