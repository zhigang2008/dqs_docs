<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<script	src="<c:url value="/script/validation/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/additional_methods.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/messages_cn.js"/>"	type="text/javascript"></script>
<script	src="<s:url value="/script/swfobject.js"/>"	type="text/javascript"></script>
<script	src="<s:url value="/script/uploadify/jquery.uploadify.min.js"/>"	type="text/javascript"></script>
<script type="text/javascript"> 
        $(document).ready(function() {
        	
            //表单验证
            var v = $("#myForm").validate({

            	/*默认条件即可,如果需要请在下面启用相关选项
            	debug: false,
    			errorElement: "em",
    			success: function(label) {
    				label.text("ok!").addClass("success");
    			},
    			//errorContainer: $("#warning, #summary"),
    			//errorPlacement: function(error, element) {
    			//error.appendTo( element.parent("td").next("td") );
    			//},
    			
    			//(Demo)可以通过在此处添加校验规则和校验信息,默认已经通过字段的CSS样式添加了校验信息.
    			rules: {
    				firstname: "required",
    				lastname: "required",
    				username: {
    					required: true,
    					minlength: 2
    				}
    			},
    			messages: {
    				firstname: "Please enter your firstname",
    				lastname: "Please enter your lastname",
    				username: {
    					required: "Please enter a username",
    					minlength: "Your username must consist of at least 2 characters"
    				}
    			}
			*/
          });

            $("#file_upload").uploadify({
                "uploader"  : "<s:url value="/script/uploadify/uploadify.swf"/>",
                "script"    : "<s:url value="/support/fileupload.do"/>",
                "cancelImg" : "<s:url value="/script/uploadify/cancle.gif"/>",
                "fileDataName" : "fileData",
                "folder"    : "/uploads",
                "fileExt"   : "*.*",
                "fileDesc"  : "All Files (*.*)",
                // "scriptData":backData,
                "auto"      : true,
                //"queueID"   : "fileQueue",  
                "multi"     : false,
                "buttonText": "Select Files...",
                //"buttonImg" : "<s:url value="/image/layout/uploadButton.gif"/>",
                "wmode"     : "transparent",
                "onComplete" :completeAction,
                "onError"   : function (event,queueID,fileObj,errorObj) {
                    alert(errorObj.type + ' Error: ' + errorObj.info);
                  }
              });
        });

        function completeAction(event, queueID, fileObj, response, data){
        	//转换为json对象
        	 var json = eval("(" + response + ")");
        	 $("#fileSize").attr("value",fileObj.size);
             $("#documentName").attr("value",json.dataMap.fileName);
             $("#documentFile").attr("value",json.dataMap.filePath);
        }
</script>          
<title>应急预案</title>
</head>

<body>
    <!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>应急预案 -> 提交</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<s:form id="myForm" action="EmergencyPlan/save4submitter.do" namespace="/office" method="post">
		<div id="controlbuttons" class="buttons">
               <button id="submitButton" name="submitButton" type="submit"  class="positive">
                 <img src="<c:url value="/image/form/ok.gif"/>" alt="" /> 提交
               </button>
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/office/EmergencyPlan/list4submitter.do'" >
                 <img src="<c:url value="/image/form/grid.gif"/>" alt="" /> 返回列表
               </button> 
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 取消
               </button> 
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
         <p>上传文件</p>
		<div id="tips" ></div>
		<div>
		  <input id="file_upload" name="file_upload" type="file"  /> 
		</div>
		<!-- <div id="fileQueue" style="width: 400px;border: 1px solid green;min-height:60px;"></div> -->
	    
        <p>基本信息:</p>
        <!-- 隐藏信息 -->
        <s:hidden label="提交人" key="submitterId" value="%{#session.CURRENT_USER.userid}" cssClass="required digits " required="true" />
		<s:hidden label="状态" key="status" value="1" cssClass="required  " required="true" />
		<s:hidden label="上传文件" id="documentFile" name="planFile" value="" cssClass="required" required="false" />
		<table id="formtable" class="formtable">
		<%@ include file="form_include.jsp" %>
		</table>
        </div>
	</s:form>
    </div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>