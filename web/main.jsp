<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp"%>
<link href="<c:url value="/style/main.css"/>" type="text/css"	rel="stylesheet" />
<script src="<c:url value="/script/jquery-ui.js"/>"	type="text/javascript"></script>
<!--[if IE]><script type="text/javascript" src="<c:url value="/script/flot/excanvas.min.js"/>"></script><![endif]-->
<script src="<c:url value="/script/flot/jquery.flot.min.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/script/flot/jquery.flot.pie.min.js"/>"	type="text/javascript"></script>
<title>首页</title>

</head>
<body>
<!-- Header -->
<%@ include file="/include/header.jsp"%>
<!-- End of Header -->
<!-- Page title -->
<div id="pagetitle">
<div class="wrapper">
<h1>主页面</h1>
<!--查询框,暂时取消 
			 <form action="" method="get"><input class="" type="text" id="q" name="q" /></form>
			  --></div>
</div>
<!-- End of Page title -->
<!-- 主内容 -->
<div id="content" class="cl wrapper" style="min-height: 400px;"><!-- Page content -->
<div id="page">
<div class="wrapper">

<div id="noticeDiv">
<div class="colgroup leading">
<div class="column first" style="width: 310px;">
<h4>公告</h4>
<hr />
<div id="announcementDiv"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getNews.do?typeId=1"/>", function(data){

          var content="<table width='100%' class='no-style full'><tbody>";
    	  $.each(data.Rows, function(i,item){
    	    content=content+"<tr><td class='ta-left'><a href='<s:url value="/front/showNews.do?id="/>"+item.id+"' target='_blank'>"+item.title.substring(0,20)+"</a></td><td class='ta-right'>"+item.publishTimeString+"</td></tr>";
    	  });
    	  content=content+"</tbody></table>";
    	  $("#announcementDiv").html(content);
    	});
     </script>
<div class="column " style="width: 310px;">
<h4>新闻发布</h4>
<hr />
<div id="newsContentDiv"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getNews.do?typeId=21"/>", function(data){

          var content="<table width='100%' class='no-style full'><tbody>";
    	  $.each(data.Rows, function(i,item){
    	    content=content+"<tr><td class='ta-left'><a href='<s:url value="/front/showNews.do?id="/>"+item.id+"' target='_blank'>"+item.title.substring(0,20)+"</a></td><td class='ta-right'>"+item.publishTimeString+"</td></tr>";
    	  });
    	  content=content+"</tbody></table>";
    	  $("#newsContentDiv").html(content);
    	});
     </script>
<div class="column " style="width: 300px;">
<h4>通知</h4>
<hr />
<div id="notesContentDiv"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getNews.do?typeId=22"/>", function(data){

          var content="<table width='100%' class='no-style full'><tbody>";
    	  $.each(data.Rows, function(i,item){
    	    content=content+"<tr><td class='ta-left'><a href='<s:url value="/front/showNews.do?id="/>"+item.id+"' target='_blank'>"+item.title.substring(0,20)+"</a></td><td class='ta-right'>"+item.publishTimeString+"</td></tr>";
    	  });
    	  content=content+"</tbody></table>";
    	  $("#notesContentDiv").html(content);
    	});
     </script></div>

</div>

<!-- 报表区域 -->
<div id="reportDiv" class="cl">
<h4>Reports</h4>
<hr />
<!-- 应急方案类型统计 -->
<div id="emergencyPlanCount" class="content-box ">
<h3>应急方案类型统计</h3>
<div id="emergencyPlanCountContent"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getEmergencyPlanCount"/>", function(data){

          var content="<table width='100%'>";
    	  $.each(data, function(i,item){
    	    content=content+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    	  });
    	  content=content+"</table>";
    	  $("#emergencyPlanCountContent").html(content);
    	});
     </script> <!-- 应急方案状态统计 -->
<div id="emergencyPlanStatus" class="content-box">
<h3>应急方案状态统计</h3>
<div id="emergencyPlanStatustContent"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getEmergencyPlanStatusCount"/>", function(data){

          var statusContent="<table width='100%'>";
    	  $.each(data, function(i,item){
    		  statusContent=statusContent+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    	  });
    	  statusContent=statusContent+"</table>";
    	  $("#emergencyPlanStatustContent").html(statusContent);
    	});
     </script> <!-- 标准工作程序统计 -->
<div id="standardworkType" class="content-box">
<h3>标准工作程序类型统计</h3>
<div id="standardworkTypeContent"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getStandardworkTypeCount"/>", function(data){

          var content="<table width='100%'>";
    	  $.each(data, function(i,item){
    		  content=content+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    	  });
    	  content=content+"</table>";
    	  $("#standardworkTypeContent").html(content);
    	});
     </script>

<!-- 标准工作程序状态统计 -->
<div id="standardworkStatusType" class="content-box">
<h3>标准工作程序状态统计</h3>
<div id="standardworkStatusContent"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getStandardworkStatusCount"/>", function(data){

          var content="<table width='100%'>";
    	  $.each(data, function(i,item){
    		  content=content+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    	  });
    	  content=content+"</table>";
    	  $("#standardworkStatusContent").html(content);
    	});
     </script>    
    
<!-- 救援队统计 -->
<div id="rescueTeamTypeCount" class="content-box first">
<h3>救援队类型统计</h3>
<div id="rescueTeamTypeContent" style="min-height:150px;"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getRescueTeamTypeCount"/>", function(data){

          //var content="<table width='100%'>";
           var plotdata = [];
    	  $.each(data, function(i,item){
    		 // content=content+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    		  plotdata[i] = {label: item.NAME, data:item.AMOUNT };
    	  });
    	  //content=content+"</table>";
    	 // $("#rescueTeamTypeContent").html(content);
    	 
    	  //图形
    	  $.plot($("#rescueTeamTypeContent"), plotdata, 
    			  {
    			          series: {
    			              pie: { 
    			                  show: true,
    			                  radius: 1,
    			                  label: {
    			                      show: true,
    			                      radius: 2/3,
    			                      formatter: function(label, series){
    			                          return '<div style="font-size:8pt;text-align:center;padding:2px;color:black;">'+label+'<br/>'+Math.round(series.percent)+'%('+series.data[0][1]+')</div>';
    			                      },
    			                      threshold: 0.1
    			                  }
    			              }
    			          },
    			          legend: {
    			              show: false
    			          }
    			  });
    	});
     </script>
<!-- 救援装备统计 -->
<div id="rescueEquipmentTypeCount" class="content-box">
<h3>救援装备类型统计</h3>
<div id="rescueEquipmentTypeContent" style="min-height:150px;"></div>
</div>
<script type="text/javascript">
        $.getJSON("<s:url value="/front/getReport.do?reportName=getRescueEquipmentTypeCount"/>", function(data){

          //var content="<table width='100%'>";
          var plotdata = [];
       	  $.each(data, function(i,item){
    		 // content=content+"<tr><td width='50%'>"+item.NAME+"</td><td>"+item.AMOUNT+"</td></tr>";
    		  plotdata[i] = {label: item.NAME, data:item.AMOUNT };
    	  });
    	  //content=content+"</table>";
    	  //$("#rescueEquipmentTypeContent").html(content);

    	  //图形
    	  $.plot($("#rescueEquipmentTypeContent"), plotdata, 
    			  {
    			          series: {
    			              pie: { 
    			                  show: true,
    			                  radius: 1,
    			                  label: {
    			                      show: true,
    			                      radius: 2/3,
    			                      formatter: function(label, series){
    			                          return '<div style="font-size:8pt;text-align:center;padding:2px;color:black;">'+label+'<br/>'+Math.round(series.percent)+'%('+series.data[0][1]+')</div>';
    			                      },
    			                      threshold: 0.1
    			                  }
    			              }
    			          },
    			          legend: {
    			              show: false
    			          }
    			  });
    	});
 </script>


 
 <!-- end of reportDiv -->    
 </div>

</div>

</div>
</div>
<!-- footer -->
<%@ include file="/include/footer.jsp"%>
<!-- End of footer -->
</body>
</html>


