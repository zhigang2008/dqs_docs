/**
 * Classname:com.steven.framework.tag.FileSizeTag
 * Version info:V1.0
 * Date:2011-10-10 
 * Copyright notice: minshengLife
 */
package com.steven.framework.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.steven.framework.util.StrUtils;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class TextCutTag extends BodyTagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 8300701273118894833L;

    private final Log log = LogFactory.getLog(TextCutTag.class);
    
    /**
     * 保留字符串长度
     */
    private int length=15;
    /**
     * 
     */
    public TextCutTag() {
	// TODO Auto-generated constructor stub
    }
    
   
    @Override
    public int doAfterBody() throws JspException {
	    BodyContent bodyContent = super.getBodyContent();
	    JspWriter writer = bodyContent.getEnclosingWriter();
	    String content = bodyContent.getString();
	try {
	    
	    if (content != null) {
		String changeStr=StrUtils.textCut(content, length, "...");
		writer.print(changeStr);

	    }
	} catch (Exception e) {
	       if(log.isDebugEnabled()){
		   log.debug("输出失败");   
	       }
   
	}
	    return SKIP_BODY;

    }


    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }


    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }


}
