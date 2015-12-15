/**
 * Classname:com.steven.framework.common.menu.SimpleMenu
 * Version info:V1.0
 * Date:2011-4-26 
 * Copyright notice: steven
 */
package com.steven.framework.common.menu;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**菜单的简单实现
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class SimpleMenu implements Menu {
    /**
     * 
     */
    private static final long serialVersionUID = -2706779812736999052L;
    
    private  Log log=LogFactory.getLog(getClass());
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单ID
     */
    private String id;
    /**
     * 菜单链接地址
     */
    private String link;
    /**
     * 父节点ID
     */
    private String parentId;
    /**
     * 排序字段
     */
    private int sortOrder;
    /**
     * 下级菜单
     */
    private Set<Menu> childs=new TreeSet<Menu>(new MenuComparator());

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChilds(Set<Menu> childs) {
        this.childs = childs;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /* (non-Javadoc)
     * @see com.steven.framework.common.menu.Menu#getChilds()
     */
    @Override
    public Set<Menu> getChilds() {
	return this.childs;
    }

    /* (non-Javadoc)
     * @see com.steven.framework.common.menu.Menu#getId()
     */
    @Override
    public String getId() {
	return this.id;
	}

    /* (non-Javadoc)
     * @see com.steven.framework.common.menu.Menu#getLink()
     */
    @Override
    public String getLink() {
    	if(this.link.startsWith("/")){
    		this.link=this.link.substring(1,this.link.length());
    	}
	return this.link;
    }

    /* (non-Javadoc)
     * @see com.steven.framework.common.menu.Menu#getName()
     */
    @Override
    public String getName() {
	return this.name;
    }
    
    /**添加下级菜单
     * @param child
     */
    public void addChild(Menu child){
	
	this.childs.add(child);

    }

    @Override
    public String getParentId() {
	return this.parentId;
    }

    public void setParentId(String parentId) {
	this.parentId = parentId;
    }

    
    @Override
    public int getSortOrder() {
	return this.sortOrder;
    }

    @Override
    public void setSortOrder(int sortOrder) {
	this.sortOrder=sortOrder;
	
    }
    
    /**自定义排序器
     * @author Steven
     * @version 1.0
     * @since 1.0
     */
    private class MenuComparator implements Comparator<Menu>,Serializable {
	 
	     /**
	 * 
	 */
	private static final long serialVersionUID = 3762762918149439718L;

	    public int compare(Menu m1, Menu m2) {
	        if (m1.getSortOrder()== m2.getSortOrder()) {
	              if(m1.getId().compareTo(m2.getId())>0){
	        	  return 1;
	              }else{
	        	  return -1;
	              }
	        }else if(m1.getSortOrder() > m2.getSortOrder()) {
	              return 1;
	        }else{
	              return -1;
	        }
	       
	    }
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof SimpleMenu))
	    return false;
	SimpleMenu other = (SimpleMenu) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    
}
