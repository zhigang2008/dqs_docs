/**
 * Classname:com.steven.framework.base.BaseEntity
 * Version info:V1.0
 * Date:2011-4-20 
 * Copyright notice: steven
 */
package com.steven.framework.base;

/**应用框架的基本实体类
 * @author Steven
 * @version v1.0
 * @since v1.0
 */
public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	/**
	 * 日期格式
	 */
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间格式
	 */
	protected static final String TIME_FORMAT = "HH:mm:ss";
	
	/**
	 * 日期时间格式
	 */
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 时间戳格式
	 */
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
}
