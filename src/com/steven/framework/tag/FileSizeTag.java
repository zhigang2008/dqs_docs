/**
 * Classname:com.steven.framework.tag.FileSizeTag
 * Version info:V1.0
 * Date:2011-10-10 
 * Copyright notice: minshengLife
 */
package com.steven.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.steven.framework.util.FileSizeUtils;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class FileSizeTag extends BodyTagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 5085789588116150867L;
    private final Log log = LogFactory.getLog(FileSizeTag.class);
    /**
     * 
     */
    public FileSizeTag() {
	// TODO Auto-generated constructor stub
    }
   
    @Override
    public int doAfterBody() throws JspException {
	    BodyContent bodyContent = super.getBodyContent();
	    JspWriter writer = bodyContent.getEnclosingWriter();
	    String content = bodyContent.getString();
	try {
	    
	    if (content != null) {
		String humanreadSize=FileSizeUtils.getHumanReadableFileSize(new Long(content));
		writer.print(humanreadSize);

	    }
	} catch (Exception e) {
	    
	    if(log.isDebugEnabled()){
		   log.debug("FileSize 转换失败,输出原始数据.");   
	       }
	       
		try {
		    writer.print(content);
		} catch (IOException e1) {
		    if(log.isDebugEnabled()){
			   log.debug("FileSize 显示失败.");   
		       }
		}
	   
	}
	    return SKIP_BODY;

    }


}
