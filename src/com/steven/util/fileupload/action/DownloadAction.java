package com.steven.util.fileupload.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.steven.framework.base.BaseStruts2Action;
/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class DownloadAction extends BaseStruts2Action  {
	
   /**
     * 
     */
   private static final long serialVersionUID = -3203516373682501367L;
   private String filePath;
   private String fileName;
   private String downLoadFileName;
   
    public String execute() {

	 inputStream=ServletActionContext.getServletContext().getResourceAsStream(filePath);
         //判断文件是否存在
	 if(inputStream==null){
	      return "fileNotExist";
	 }
	 return SUCCESS;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**获取下载文件名
     * @return
     */
    public String getDownLoadFileName() {
	String extend=filePath.substring(filePath.lastIndexOf("."));
	downLoadFileName=fileName.replaceAll(" ", "_");
	downLoadFileName=downLoadFileName+extend;
	try {
	    downLoadFileName=new String(downLoadFileName.getBytes(),"ISO8859-1");
	} catch (UnsupportedEncodingException e) {
	    log.warn("文件名编码转换失败");
	    
	}
	log.debug(filePath);
	log.debug(fileName);
	return downLoadFileName;
    }

	
}
