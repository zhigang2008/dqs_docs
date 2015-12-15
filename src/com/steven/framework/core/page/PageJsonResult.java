/**
 * Classname:com.steven.framework.core.page.PageJsonResult
 * Version info:V1.0
 * Date:2011-8-9 
 * Copyright notice: minshengLife
 */
package com.steven.framework.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class PageJsonResult implements Serializable {
    
 /**
 * 查询记录(列表类)
 */
private List result=null;
/**
 * 返回的数据记录
 */
private Map  dataMap=new HashMap();
 /**
 * 记录总数
 */
private int total=0;
 /**
 * 每页记录数
 */
private int pageSize=0;
 /**
 * 是否成功
 */
private boolean success=true;
/**
 * 返回信息
 */
private String  message=null;
/**
 * @return the result
 */
@JSON (name="Rows" )
public List getResult() {
    return result;
}
/**
 * @param result the result to set
 */
public void setResult(List result) {
    this.result = result;
}
/**
 * @return the total
 */

@JSON (name="Total" )
public int getTotal() {
    return total;
}
/**
 * @param total the total to set
 */
public void setTotal(int total) {
    this.total = total;
}
/**
 * @return the pageSize
 */
public int getPageSize() {
    return pageSize;
}
/**
 * @param pageSize the pageSize to set
 */
public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
}

/**将Set结果存入List
 * @param resultSet
 */
public void setResult(Set resultSet){
	this.result=new ArrayList();
	this.result.addAll(resultSet);
}
/**
 * @return the success
 */
public boolean isSuccess() {
    return success;
}
/**
 * @param success the success to set
 */
public void setSuccess(boolean success) {
    this.success = success;
}
/**
 * @return the message
 */
public String getMessage() {
    return message;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
    this.message = message;
}
/**
 * @return the dataMap
 */
public Map getDataMap() {
	return dataMap;
}
/**
 * @param dataMap the dataMap to set
 */
public void setDataMap(Map dataMap) {
	this.dataMap = dataMap;
}
}
