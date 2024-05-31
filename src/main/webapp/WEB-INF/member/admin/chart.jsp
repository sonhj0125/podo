<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
   String ctxPath = request.getContextPath(); 
        // MyMVC
%>

<jsp:include page="../../header.jsp" />    

<style type="text/css">

	.highcharts-figure,
	.highcharts-data-table table {
    min-width: 310px;
    max-width: 800px;
    margin: 1em auto;
}

#container {
    height: 400px;
}

.highcharts-data-table table {
    font-family: Verdana, sans-serif;
    border-collapse: collapse;
    border: 1px solid #ebebeb;
    margin: 10px auto;
    text-align: center;
    width: 100%;
    max-width: 500px;
}

.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}

.highcharts-data-table th {
    font-weight: 600;
    padding: 0.5em;
}

.highcharts-data-table td,
.highcharts-data-table th,
.highcharts-data-table caption {
    padding: 0.5em;
}

.highcharts-data-table thead tr,
.highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}

.highcharts-data-table tr:hover {
    background: #f1f7ff;
}
	
</style>

<script src="<%= ctxPath%>/Highcharts-10.3.1/code/highcharts.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/exporting.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/export-data.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/accessibility.js"></script>


<figure class="highcharts-figure">
    <div id="container"></div>
 
</figure>

<script type="text/javascript">


$.ajax({
	url:"<%= ctxPath%>/member/admin/chartJSON.wine",
	dataType:"json",
	success: function(json){
		console.log(JSON.stringify(json));
		/*
			[{"ptype":"로제","ordersum":"0"},
			 {"ptype":"스파클링","ordersum":"5"},
			 {"ptype":"레드","ordersum":"0"},
			 {"ptype":"화이트","ordersum":"0"}]
		*/
		
		let resultArr = [];
		let resultArr2 = [];
		
		for(let i=0; i<json.length; i++){
			
			resultArr.push(json[i].ptype); // 배열 속에 객체 넣어주기
			resultArr2.push(Number(json[i].ordersum));
		}
			
			
		
		///////////////////////////////////////////////////////////
		Highcharts.chart('container', {
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: '와인 타입별 판매량 수',
		        align: 'left'
		    },
		    xAxis: {
		        categories: resultArr,
		        crosshair: true,
		        accessibility: {
		            description: 'Countries'
		        }
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '개수'
		        }
		    },
		    tooltip: {
		        valueSuffix: ' (개수)'
		    },
		    plotOptions: {
		        column: {
		            pointPadding: 0.2,
		            borderWidth: 0
		        }
		    },
		    series: [
		        {
		            name: '와인타입별 판매량',
		            data: resultArr2
		        }
		    ]
		});

				
	},
	error: function(request, status, error){
        alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
     }
	
	
});



</script>


<jsp:include page="../../footer.jsp" />    