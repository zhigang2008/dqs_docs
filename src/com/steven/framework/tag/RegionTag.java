/**
 * Classname:com.steven.framework.tag.RegionTag
 * Version info:V1.0
 * Date:2011-9-28 
 * Copyright notice: minshengLife
 */
package com.steven.framework.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.steven.core.model.Region;
import com.steven.core.service.RegionManager;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class RegionTag extends SimpleTagSupport {

    private final Log log = LogFactory.getLog(getClass());
    
     private String firstName;
     private String firstId;
     private String secondName;
     private String secondId;
     private String thirdName;
     private String thirdId;
     private String cssClass;
     private String firstValue;
     private String secondValue;
     private String thirdValue;
     
     //提供给查询标签用
     private String headerKey;
     private String headerValue;

     
     
     /**
     * 区域管理对象,通过spring 自动匹配
     */
    private RegionManager regionManager;
    
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException {
	
	//获取请求路径
	JspContext jspContext=getJspContext();
	HttpServletRequest request = (HttpServletRequest)((PageContext) jspContext).getRequest(); 
	String contentPath=request.getContextPath();
	
	//获取spring的web context
	ServletContext servletContext = ((PageContext)jspContext).getServletContext();
	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	this.setRegionManager((RegionManager)wac.getBean("regionManager"));	
		
	JspWriter out=jspContext.getOut();
	try{
	out.println(generateElements());
	out.println(generateScripts(contentPath));
	}catch(Exception e){
	    out.println("Error!");
	    log.error("Error in RegionTag."+e.getMessage(),e);
	}
    }

    
    /**生成页面元素
     * @return
     */
    private String generateElements(){
	StringBuffer elementStr=new StringBuffer();
	List<Region> topRegionList=regionManager.getTopRegions();
	elementStr.append("<select id=\"").append(this.getFirstId()).append("\" name=\"").append(this.getFirstName())
	.append("\" class=\"").append(this.getCssClass()).append("\"  > \n");
	   
	//写入header
	if(this.headerKey!=null){
		elementStr.append("<option value=\"").append(this.getHeaderKey()).append("\" >")
		.append(this.getHeaderValue())
		.append("</option> \n");
	}
	for(Region region:topRegionList){
	    elementStr.append("<option value=\"").append(region.getId()).append("\" ");
	    if(this.hasFirstValue()&&this.getFirstValue().equals(String.valueOf(region.getId()))){
		elementStr.append(" selected ");
	    }
	    elementStr.append("> ").append(region.getRegionName()).append("</option> \n");
	}
	elementStr.append("</select> \n");
	
	//second element
	if(hasSecondElement()){
	    elementStr.append("<select id=\"").append(this.getSecondId()).append("\" name=\"").append(this.getSecondName())
		.append("\" class=\"").append(this.getCssClass()).append("\"  >\n");
	  //写入header
		if(this.headerKey!=null){
			elementStr.append("<option value=\"").append(this.getHeaderKey()).append("\" >")
			.append(this.getHeaderValue())
			.append("</option> \n");
		}
		//如果设初始值,则初始化
	    if(hasFirstValue()){
		Region firstRegion=new Region();
		firstRegion.setId(new Long(this.getFirstValue()));
		List<Region> secondRegionList=regionManager.getChilds(firstRegion);
		for(Region region:secondRegionList){
		    elementStr.append("<option value=\"").append(region.getId()).append("\" ");
		    if(hasSecondValue()&&this.getSecondValue().equals(String.valueOf(region.getId()))){
			elementStr.append(" selected ");
		    }
		    elementStr.append("> ").append(region.getRegionName()).append("</option> \n");
		}
	    }
	    elementStr.append("</select> \n");
	
	//third element
	if(hasThirdElement()){
	    elementStr.append("<select id=\"").append(this.getThirdId()).append("\" name=\"").append(this.getThirdName())
	    .append("\" class=\"").append(this.getCssClass()).append("\"  >\n");
	  
	    //写入header
		if(this.headerKey!=null){
			elementStr.append("<option value=\"").append(this.getHeaderKey()).append("\" >")
			.append(this.getHeaderValue())
			.append("</option> \n");
		}
		
		//如果设初始值,则初始化
	    if(hasFirstValue()&& hasSecondValue()){
		Region secondRegion=new Region();
		secondRegion.setId(new Long(this.getSecondValue()));
		List<Region> thirdRegionList=regionManager.getChilds(secondRegion);
		for(Region region:thirdRegionList){
		    elementStr.append("<option value=\"").append(region.getId()).append("\" ");
		    if(hasThirdValue()&&this.getThirdValue().equals(String.valueOf(region.getId()))){
			elementStr.append(" selected ");
		    }
		    elementStr.append("> ").append(region.getRegionName()).append("</option> \n");
		}
	    }
	    elementStr.append("</select> \n");
	}//end third element 
	
       }//end second element
	return elementStr.toString();
    }
    
    /**生成级联基表
     * @param contentPath  web基本路径
     * @return
     */
    private String generateScripts(String contentPath){
	StringBuffer scriptStr=new StringBuffer();
	scriptStr.append("<script type=\"text/javascript\"> \n");

	//二级
	if(hasSecondElement()){
	   scriptStr.append("$(document).ready(function(){ \n")           
           .append(" $(\"#" ).append(this.getFirstId()).append("\").change(function(){ \n")                
           .append(" $.getJSON(\"").append(contentPath).append("/system/Region/jsonGetChilds.do\",{id:$(this).val()},function(myJSON){ \n")                    
           .append(" var myOptions=\"\"; \n") ;
		//写入header
		if(this.headerKey!=null){
			scriptStr.append("  myOptions += '<option value=\"").append(this.getHeaderKey()).append("\" >")
			.append(this.getHeaderValue())
			.append("</option>'; \n");
		}
           
		scriptStr.append(" for(var i=0;i<myJSON.length;i++){ \n")                       
           .append("      myOptions += '<option value=\"' + myJSON[i].id + '\">' +myJSON[i].regionName + '</option>'; \n")                    
           .append("     }                          \n ")
           .append("  $(\"#").append(this.getSecondId()).append("\").empty();         \n ");
           if(hasThirdElement()){
               scriptStr.append("  $(\"#").append(this.getThirdId()).append("\").empty();     \n ");
            }
           scriptStr.append("  $(\"#").append(this.getSecondId()).append("\").html(myOptions); \n ")
           .append("  $(\"#").append(this.getSecondId()).append("\").change();        \n ")                
           .append("  });                           \n ");
   	    scriptStr.append("  });                           \n ");
           
           //三级元素存在
           if(hasThirdElement()){
               scriptStr.append(" $(\"#" ).append(this.getSecondId()).append("\").change(function(){ \n") 
               .append(" if($(this).val()!=null){ \n")
               .append(" $.getJSON(\"").append(contentPath).append("/system/Region/jsonGetChilds.do\",{id:$(this).val()},function(myJSON){ \n")                    
               .append(" var myOptions2=\"\"; \n");
             //写入header
       		if(this.headerKey!=null){
       			scriptStr.append("  myOptions2 += '<option value=\"").append(this.getHeaderKey()).append("\" >")
       			.append(this.getHeaderValue())
       			.append("</option>'; \n");
       		}
       		scriptStr.append(" for(var i=0;i<myJSON.length;i++){ \n")                       
               .append("      myOptions2 += '<option value=\"' + myJSON[i].id + '\">' +myJSON[i].regionName + '</option>'; \n")                    
               .append("     }                          \n ")
               .append("  $(\"#").append(this.getThirdId()).append("\").empty();         \n ")
               .append("  $(\"#").append(this.getThirdId()).append("\").html(myOptions2); \n ");
               scriptStr.append("  });                           \n ")
               .append("     }                          \n ");
               scriptStr.append("  });                           \n ");
            }
           
           if(!hasFirstValue()){
             scriptStr.append("  $(\"#").append(this.getFirstId()).append("\").change();        \n ");
           }
           //end for $(function()
           scriptStr.append("  });                           \n ");
	}
	
	scriptStr.append(" </script> \n ");
	return scriptStr.toString();
    }

    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return the firstId
     */
    public String getFirstId() {
	if(this.firstId==null || this.firstId.trim().equals("")){
	    this.firstId=this.getFirstName();
	}
        return firstId;
    }


    /**
     * @param firstId the firstId to set
     */
    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }


    /**
     * @return the secondName
     */
    public String getSecondName() {
        return secondName;
    }


    /**
     * @param secondName the secondName to set
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    /**
     * @return the secondId
     */
    public String getSecondId() {
	if(this.secondId==null || this.secondId.trim().equals("")){
	    this.secondId=this.getSecondName();
	}
        return secondId;
    }


    /**
     * @param secondId the secondId to set
     */
    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }


    /**
     * @return the thirdName
     */
    public String getThirdName() {
        return thirdName;
    }


    /**
     * @param thirdName the thirdName to set
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }


    /**
     * @return the thirdId
     */
    public String getThirdId() {
	if(this.thirdId==null || this.thirdId.trim().equals("")){
	    this.thirdId=this.getThirdName();
	}
        return thirdId;
    }


    /**
     * @param thirdId the thirdId to set
     */
    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }


    /**
     * @return the cssClass
     */
    public String getCssClass() {
	if(cssClass==null){
	    cssClass="";
	}
        return cssClass;
    }


    /**
     * @param cssClass the cssClass to set
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }



    /**
     * @param regionManager the regionManager to set
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }


    /**
     * @return the firstValue
     */
    public String getFirstValue() {
        return firstValue;
    }


    /**
     * @param firstValue the firstValue to set
     */
    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }


    /**
     * @return the secondValue
     */
    public String getSecondValue() {
        return secondValue;
    }


    /**
     * @param secondValue the secondValue to set
     */
    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }


    /**
     * @return the thirdValue
     */
    public String getThirdValue() {
        return thirdValue;
    }


    /**
     * @param thirdValue the thirdValue to set
     */
    public void setThirdValue(String thirdValue) {
        this.thirdValue = thirdValue;
    }

    
    /**是否有第二个Element
     * @return
     */
    public boolean hasSecondElement(){
	if(this.getSecondName()!=null &&!"".equals(this.getSecondName().trim())){
	    return true;
	}
	else
	    return false;
    }
    
    /**是否有第三级Element
     * @return
     */
    public boolean hasThirdElement(){
	if(this.getThirdName()!=null &&!"".equals(this.getThirdName().trim())){
	    return true;
	}
	else
	    return false;
    }
    
    public boolean hasFirstValue(){
	if(this.getFirstValue()!=null &&!"".equals(this.getFirstValue().trim())){
	    return true;
	}
	else
	    return false;
    }
    /**是否有第二个Element
     * @return
     */
    public boolean hasSecondValue(){
	if(this.getSecondValue()!=null &&!"".equals(this.getSecondValue().trim())){
	    return true;
	}
	else
	    return false;
    }
    
    /**是否有第三级Element
     * @return
     */
    public boolean hasThirdValue(){
	if(this.getThirdValue()!=null &&!"".equals(this.getThirdValue().trim())){
	    return true;
	}
	else
	    return false;
    }
    
    /**测试
     * @param params
     */
    public static void main(String[] params){
	RegionTag tag=new RegionTag();
	tag.setFirstName("province");
	tag.setSecondName("city");
	tag.setThirdName("district");
	System.out.println(tag.generateScripts("webapp"));
    }


	/**
	 * @return the headerKey
	 */
	public String getHeaderKey() {
		if(this.headerKey==null){
			return "";
		}
		return headerKey;
	}


	/**
	 * @return the headerValue
	 */
	public String getHeaderValue() {
		if(this.headerValue==null){
			return "";
		}
		return headerValue;
	}


	/**
	 * @param headerKey the headerKey to set
	 */
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}


	/**
	 * @param headerValue the headerValue to set
	 */
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}




}
