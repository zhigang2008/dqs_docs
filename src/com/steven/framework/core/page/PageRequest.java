package com.steven.framework.core.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
/**
 * 分页请求信息
 * 其中范型<T>为filters的类型
 * @author Steven
 */
public class PageRequest<T> implements Serializable {

	/**
	 * 页号码,页码从1开始
	 */
	private int pageNumber=0;
	/**
	 * 分页大小
	 */
	private int pageSize=0;
	/**
	 * 排序的多个列,如: username desc
	 */
	private String sortColumns;
	
	public PageRequest() {
		
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getSortColumns() {
		return sortColumns;
	}
	/**
	 * 排序的列,可以同时多列,使用逗号分隔,如 username desc,age asc
	 * @return
	 */
	public void setSortColumns(String sortColumns) {
		checkSortColumnsSqlInjection(sortColumns);
		if(sortColumns != null && sortColumns.length() > 50) {
			throw new IllegalArgumentException("sortColumns.length() <= 50 must be true");
		}
		this.sortColumns = sortColumns;
	}

	/**
	 * 将sortColumns进行解析以便返回SortInfo以便使用
	 * @return
	 */
	public List<SortInfo> getSortInfos() {
		return Collections.unmodifiableList(SortInfo.parseSortColumns(sortColumns));
	}
	
	private void checkSortColumnsSqlInjection(String sortColumns) {
		if(sortColumns == null) return;
		if(sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0) {
			sortColumns.replaceAll("'", "");
			throw new IllegalArgumentException("sortColumns:"+sortColumns+" has SQL Injection risk");
		}
	}
}
