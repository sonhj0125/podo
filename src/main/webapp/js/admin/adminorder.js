$(function() {

	$("tr.memberInfo").bind("click",function(){

		const ctxPath = $("div#ctxPath").text();
		const oindex = $(this).find("#oindex").text();
		let url = `${ctxPath}/member/admin/deliveryinfo.wine?oindex=${oindex}`;

		$('#iframe_delivery').attr('src', url);

	});

});

function doSearch(ctxPath){
	
	const frm = document.member_search_frm;
	
	frm.method = "get";
	frm.action = `${ctxPath}/member/admin/adminOrder.wine`;
	frm.submit();
	
}