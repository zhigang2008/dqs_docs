package com.steven.framework.base;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionSupport;
import com.steven.framework.common.beanutils.BeanUtils;
import com.steven.framework.common.codetable.GlobalCodeTable;
import com.steven.framework.core.page.Page;
import com.steven.framework.core.page.PageJsonResult;
import com.steven.framework.core.page.PageRequest;
import com.steven.framework.core.page.PageRequestFactory;
import com.steven.framework.util.ConvertRegisterHelper;
import com.steven.framework.util.ObjectUtils;

public abstract class BaseStruts2Action extends ActionSupport implements RequestAware {
	/**
	 * logger日志,可以供子类使用
	 */
	protected Log log=LogFactory.getLog(getClass());
	
	//定义一些固定的页面
	//forward paths
	protected static final String LIST_JSP= "list";
	protected static final String CREATE_JSP = "create";
	protected static final String EDIT_JSP = "edit";
	protected static final String SHOW_JSP = "show";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "list_action";
	//json result
	protected static final String JSONRESULT = "jsonResult";
	protected static final String JSONPAGELIST = "jsonPageList";
	protected static final String JSONLIST = "jsonList";
	protected static final String TREELIST = "treeList";
	//远程校验所使用的直接stream resultType
	protected static final String CHECKRESULT = "checkResult";
	
	
	/**
	 * requestMap
	 */
	protected Map requestMap = null;
	
	/**
	 * 用来存放一些基础代码列表类常量
	 */
	protected Map baseCode =new HashMap();
	/**
	 * 基础代码的辅助类,查找各基础代码
	 */
	protected GlobalCodeTable globalCodeTable;
	/**
	 * 封装了Json形式的返回结果对象
	 */
	private PageJsonResult jsonResult=new PageJsonResult();
	
	static {
		//注册converters
		ConvertRegisterHelper.registerConverters();
	}
	
	/**属性复制操作
	 * @param target 目标对象
	 * @param source 源对象
	 */
	public void copyProperties(Object target,Object source) {
		BeanUtils.copyProperties(target, source);
	}

	/**属性复制
	 * @param <T>  类型
	 * @param destClass 目标对象
	 * @param orig 源对象
	 * @return  生成的目标对象
	 */
	public <T> T copyProperties(Class<T> destClass,Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}
	
	public void setRequest(Map request) {
		this.requestMap = request;
	}

	/**保存分页查询数据对象
	 * @param page
	 * @param pageRequest
	 */
	public void savePage(Page page,PageRequest pageRequest){
		savePage("",page,pageRequest);
	}
	
	/**
	 * 用于一个页面有多个extremeTable是使用
	 * @param tableId 等于extremeTable的tableId属性
	 */
	public void savePage(String tableId,Page page,PageRequest pageRequest){
		Assert.notNull(tableId,"tableId must be not null");
		Assert.notNull(page,"page must be not null");
		
		getRequest().setAttribute(tableId+"page", page);
		getRequest().setAttribute(tableId+"totalRows", new Integer(page.getTotalCount()));
		getRequest().setAttribute(tableId+"pageRequest", pageRequest);
		getRequest().setAttribute(tableId+"query", pageRequest);
	}
	
	/**产生新的查询对象
	 * @param <T>
	 * @param queryClazz
	 * @param defaultSortColumns
	 * @return
	 */
	public <T extends PageRequest> T newQuery(Class<T> queryClazz,String defaultSortColumns){
		PageRequest query = PageRequestFactory.bindPageRequest(org.springframework.beans.BeanUtils.instantiateClass(queryClazz),ServletActionContext.getRequest(),defaultSortColumns);
		return (T)query;
    }
	public <T extends PageRequest> T newQuery(Class<T> queryClazz,String defaultSortColumns,int pageSize){
		PageRequest query = PageRequestFactory.bindPageRequest(org.springframework.beans.BeanUtils.instantiateClass(queryClazz),ServletActionContext.getRequest(),defaultSortColumns,pageSize);
		return (T)query;
    }
	
	public boolean isNullOrEmptyString(Object o) {
		return ObjectUtils.isNullOrEmptyString(o);
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
//------------------------Json result 服务管理------------------------

	/**返回Json对象
	 * @return the jsonResult
	 */
	public PageJsonResult getJsonResult() {
	    return jsonResult;
	}
	
	/**将分页查询结果存入JSON对象
	 * @param page
	 * @param pageRequest
	 */
	public void saveJsonResult(Page page,PageRequest pageRequest){
	    jsonResult=new PageJsonResult();
	    jsonResult.setTotal(page.getTotalCount());
	    jsonResult.setPageSize(page.getPageSize());
	    jsonResult.setResult(page.getResult());
	    
	}
	/**将列表对象存入JSON对象
	 * @param obj
	 */
	public void saveJsonResult(List obj){
		jsonResult=new PageJsonResult();
		jsonResult.setResult(obj);
	}
	
	/**将Set对象存入Json对象
	 * @param obj
	 */
	public void saveJsonResult(Set obj){
		jsonResult=new PageJsonResult();
		jsonResult.setResult(obj);
	}

	
	//-------------基础代码管理------------------
	/**获取基础数据全局类
	 * @return 全局基础数据总对象
	 */
	public Map getBaseCode() {
	    return baseCode;
	}
	/**添加固定常量列表
	 * @param key   key值
	 * @param value value值
	 */
	public void addBaseCode(String key,Object value){
	    baseCode.put(key, value);
	}
	
	/**设置全局基础代码管理服务对象
	 * @param globalCodeTable the globalCodeTable to set
	 */
	public void setGlobalCodeTable(GlobalCodeTable globalCodeTable) {
	    this.globalCodeTable = globalCodeTable;
	}
	/**获取基础代码数据,并放入全局对象中,供使用
	 * @param codeTable 基表数据对象名称,通过spring的beanName获取.如codeTable=role,则通过spring获取roleManager对象,通过roleManager.getBaseCode()获取角色列表
	 */
	public void fetchBaseCode(String codeTable){
	    addBaseCode(codeTable,globalCodeTable.getBaseCode(codeTable));
	}
	
	
	//------------------------直接数据流对象result type管理---------------------------
	/**直接输出的stream*/
	protected InputStream  inputStream=null;

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
	    return inputStream;
	}
	
	/**校验结果
	 * @param check
	 */
	protected void checkResult(boolean check){
		if(check){
			inputStream=new ByteArrayInputStream("true".getBytes());
		}else{
			inputStream=new ByteArrayInputStream("false".getBytes());
		}
	}
	
}

