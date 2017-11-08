$(function() { 
	$("#passBut").on("click",function(){
		audit(1) ;
	}) ;
	$("#refBut").on("click",function(){
		audit(2) ;
	}) ;
})
function audit(status) {
	$.post("pages/purchase/audit.action",{pid:pid,status:status},function(data){
		if (status == 1) {
			operateAlert(data.trim() == "true","购入申请通过！","购入申请拒绝！") ;
		} else {
			operateAlert(!data.trim() == "true","购入申请通过！","购入申请拒绝！") ;
		}
		$("#auditDiv").hide() ;
	},"text") ;
}