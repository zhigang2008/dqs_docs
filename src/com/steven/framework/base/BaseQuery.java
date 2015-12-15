package com.steven.framework.base;

import com.steven.framework.core.page.PageRequest;

/**基本查询类
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	
    static {
        System.out.println("BaseQuery.DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
    }
    
	/**
	 * 默认构造函数
	 */
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}
	/**使用指定的数值指定每页记录数
	 * @param pageSize 每页记录数
	 */
	public BaseQuery(int pageSize) {
		setPageSize(pageSize);
	}
	  
}
