package com.steven.util.fileupload.action;

import java.io.File;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import com.steven.framework.base.BaseStruts2Action;
/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class FileUploadAction extends BaseStruts2Action  {
	
     /**
     * 
     */
    private static final long serialVersionUID = -3819949106718298904L;
    private File[] fileData = null;
    private String[] fileDataContentType;
    private String[] fileDataFileName;
    private String folder = null;
	

    /* ------------JSON 方法------------- */

    public String upload() {
	
	try{
	    // 根据服务器的文件保存地址和原文件名创建目录文件全路径
	
	String destPath = ServletActionContext.getServletContext().getRealPath(this.getFolder());

	if (fileData != null) {
	    for (int i = 0; i < fileData.length; i++) {
		String currentFileName=fileDataFileName[i];
		
		String targetFileName = generateFileName(fileData[i].getName(), currentFileName);
		File destFile = new File(destPath + "/" + targetFileName);
		
		if(log.isDebugEnabled()){
			log.debug("destFile="+destFile);
		}
		
		try {
		    //copy到制定目录
		    FileUtil.copyFile(fileData[i], destFile);
                    //添加信息
		    this.getJsonResult().getDataMap().put("filePath", this.getFolder()+"/"+targetFileName);
		    this.getJsonResult().getDataMap().put("fileName", currentFileName.substring(0,currentFileName.lastIndexOf(".")));

		    
		} catch (IOException e) {
		    log.error("上传文件失败:" + e.getMessage(), e);
		    this.getJsonResult().setSuccess(false);
		}
		 
	    }
	}
	//返回的数据
	    this.getJsonResult().setSuccess(true);
	    
	}catch(Exception e){
	   this.getJsonResult().setSuccess(false);
	   this.getJsonResult().setMessage("上传失败:"+e.getMessage());
	}

	
	return JSONRESULT;
    }

	/**
	 * @return the filedata
	 */
	public File[] getFileData() {
	    return fileData;
	}

	/**
	 * @param filedata the filedata to set
	 */
	public void setFileData(File[] filedata) {
	    this.fileData = filedata;
	}


	/**
	 * @return the fileDataContentType
	 */
	public String[] getFileDataContentType() {
	    return fileDataContentType;
	}

	/**
	 * @param fileDataContentType the fileDataContentType to set
	 */
	public void setFileDataContentType(String[] fileDataContentType) {
	    this.fileDataContentType = fileDataContentType;
	}

	/**
	 * @return the fileDataFileName
	 */
	public String[] getFileDataFileName() {
	    return fileDataFileName;
	}

	/**
	 * @param fileDataFileName the fileDataFileName to set
	 */
	public void setFileDataFileName(String[] fileDataFileName) {
	    this.fileDataFileName = fileDataFileName;
	}

	
	/**
	 * @return the folder
	 */
	public String getFolder() {
	    return folder;
	}
	/**
	 * @param folder the folder to set
	 */
	public void setFolder(String folder) {
	    this.folder = folder;
	} 
	 /**
	 * @param uploadFileName
	 * @param fileName
	 * @return
	 */
	private String generateFileName(String uploadFileName,String fileName) {   
	      
	        int position = fileName.lastIndexOf(".");   
	        String extension = fileName.substring(position);   
	        position = uploadFileName.lastIndexOf(".");
	        String uploadName=uploadFileName.substring(0,position);
	        return uploadName + extension;   
        }

}
