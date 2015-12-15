<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<script src="<c:url value="/script/jquery-form.js"/>" type="text/javascript"></script>
<script	src="<c:url value="/script/validation/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/additional_methods.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/messages_cn.js"/>"	type="text/javascript"></script>
<script type="text/javascript"> 
        $(document).ready(function() {
            //ajax提交表单配置
        	var options = { 
        	        success:  afterAction,  // post-submit callback 
        	        dataType:  "json",        // 'xml', 'script', or 'json' (expected server response type) 
        	        resetForm: false        // reset the form after successful submit 
        	    };

            //表单验证
    		var v = $("#myForm").validate({
    			submitHandler: function(form) {
    				$(form).ajaxSubmit(options);
    		    },
    		    rules: {
    		    	isPublic: "required"
    				},
    			messages: {
    				isPublic: "请设置公开状态"
    			}

    		});
        	
        });

        //提交后提示信息
        function afterAction(data)  { 
            if(data.success){
        	   parent.closePopup();
            }
            else{
            	alert(data.message);
            }

        } 
</script>

<title>上报信息审核意见</title>
</head>

<body>
	<!-- End of Page title -->
	<div  class="cl">
	<s:form id="myForm" action="/office/ReportInformation/jsonUpdate.do" method="post">
		<s:hidden id="id" name="id" />
		<s:hidden id="status" name="status" value="%{model.status}" />
        <div id="formdiv" >
        <p>是否公开:</p>
		    <s:radio list='#{true:"公开",false:"不公开"}'  key="isPublic" value="%{model.isPublic}" ></s:radio>
	    <p>批注:</p>
	      <s:textarea rows="4" cols="60" label="批注" key="backNote" value="%{model.backNote}"  />
	    <div id="controlbuttons" class="buttons">
               <button id="submitButton" name="submitButton" type="submit"  class="positive">
                 <img src="<c:url value="/image/form/ok.gif"/>" alt="" /> 确定
               </button>
        </div>
		</div>
			
	</s:form>
	</div>
</body>
</html>