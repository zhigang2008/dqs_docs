<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.steven.framework.common.menu.Menu" %>
<%@ taglib uri="/steven-tags" prefix="wylb" %>
	<!-- Header -->
	<div id="top">
		<div class="wrapper">
			<!-- Title/Logo  -->
			<div id="title">
			<img src="<c:url value="/image/main/logo.png" />" alt="网站LOGO" width="50" height="50"/>
			<span>地震灾难紧急救援管理系统</span>
			</div>
			<!-- right navigation -->
			<div id="topnav">
				<a href="#">
				<img class="avatar" src="<c:url value="/image/main/user_32.png"/>" alt="" />
				</a>
				当前用户:<wylb:principal property="realName"/></b>
				<wylb:user>
				<s:if test="#session.CURRENT_USER.userid!=2">
				<span>|</span> <a href="<s:url value="/member/changepwd.jsp"/>">更改密码</a>
				<s:url id="userUpdateUrl" action="toUpdateUserInfo" namespace="/member" >
				  <s:param name="userid" value="%{#session.CURRENT_USER.userid}"/>
				</s:url>
				<span>|</span> <s:a href="%{userUpdateUrl}" method="post">个人信息</s:a>
				<span>|</span> <a href="<s:url value="/logout.do"/>">退出</a><br />
				</s:if>
				<s:else>
				<span>|</span> <a href="<s:url value="/logout.do"/>">登录</a><br />
				</s:else>
				<!-- notice 
				<small>You have <a href="#" class="high"><b>1</b> new message!</a></small>
				-->
				</wylb:user>
				<wylb:guest>
				<span>|</span> <a href="<s:url value="/logout.do"/>">登录</a><br />
				</wylb:guest>
				
				
			</div>
			<!-- End of Top navigation -->
			<!-- Main navigation -->
			<div id="menu_div">
			<wylb:menu id="my_menu" menu="${userMenus}" style="2" level="2" showRoot="0"  target="_self" autoInit="1"></wylb:menu>
			<!-- End of Main navigation -->
			
			<!-- Quick search box -->
			<div id="search">
			  <!--  
			  <form action="" method="post"><input class="" type="text" id="q" name="q" /></form>
			  -->
			</div>
			
			</div>
		</div>
	</div>
	<!-- End of Header -->