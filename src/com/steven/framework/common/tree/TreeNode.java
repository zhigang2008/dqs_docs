/**
 * 
 */
package com.steven.framework.common.tree;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**树形节点
 * @author Steven
 * @version 1.0
 *
 */
public class TreeNode implements Serializable {
	
	/**
	 * 节点key
	 */
	private String key=null;
	/**
	 * 节点名称
	 */
	private String title=null;
	/**
	 * 是否父节点
	 */
	private boolean isFolder=false;
	/**
	 * 链接地址
	 */
	private String url=null;
	/**
	 * 是否懒加载
	 */
	private boolean isLazy=false;
	/**
	 * 提示信息
	 */
	private String tooltip=null;
	/**
	 * 是否可被选择
	 */
	private boolean unselectable=false;
	/**
	 * 是否隐藏checkbox
	 */
	private boolean hideCheckbox=false;
	/**
	 * 父节点ID
	 */
	private String  parentId=null;
	/**
	 * 图标地址
	 */
	private String icon=null;
	
	/**
	 * 是否展开
	 */
	private boolean expand=false;
	/**
	 * Use <span> instead of <a> tag for this node 
	 */
	private boolean noLink=false;
	/**
	 * 排序序号
	 */
	private int sortOrder=1;
	/**
	 * 子节点
	 */
	private Set<TreeNode> children=new TreeSet<TreeNode>(new NodeComparator()); 
	
	/**
	 * 额外的附加信息
	 */
	private Map  extraData=new HashMap();

	/**
	 * 
	 */
	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the isFolder
	 */
	public boolean isFolder() {
		return isFolder;
	}
	
	public boolean getIsFolder() {
		return isFolder;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the isLazy
	 */
	public boolean isLazy() {
		return isLazy;
	}
	
	public boolean getIsLazy(){
		return isLazy;
	}

	/**
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @return the children
	 */
	public Set<TreeNode> getChildren() {
		return children;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param isFolder the isFolder to set
	 */
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param isLazy the isLazy to set
	 */
	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<TreeNode> children) {
		this.children = children;
	}

	/**
	 * @return the unselectable
	 */
	public boolean isUnselectable() {
		return unselectable;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return the noLink
	 */
	public boolean isNoLink() {
		return noLink;
	}

	/**
	 * @param unselectable the unselectable to set
	 */
	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @param noLink the noLink to set
	 */
	public void setNoLink(boolean noLink) {
		this.noLink = noLink;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (!(obj instanceof TreeNode))
			return false;
		TreeNode other = (TreeNode) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public void addChild(TreeNode newTreeNode) {
		this.children.add(newTreeNode);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeNode [icon=" + icon + ", isFolder=" + isFolder
				+ ", isLazy=" + isLazy + ", key=" + key + ", noLink=" + noLink
				+ ", parentId=" + parentId + ", title=" + title + ", tooltip="
				+ tooltip + ", unselectable=" + unselectable + ", url=" + url
				+ "]";
	}

	/**
	 * @return the extraData
	 */
	public Map getExtraData() {
		return extraData;
	}

	/**
	 * @param extraData the extraData to set
	 */
	public void setExtraData(Map extraData) {
		this.extraData = extraData;
	}

	/**添加额外的信息
	 * @param key
	 * @param value
	 */
	public void addExtraData(String key,Object value){
		this.extraData.put(key, value);
	}

	/**
	 * @return the expand
	 */
	public boolean isExpand() {
		return expand;
	}

	/**
	 * @param expand the expand to set
	 */
	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	
	/**自定义排序器
     * @author Steven
     * @version 1.0
     * @since 1.0
     */
    private class NodeComparator implements Comparator<TreeNode> {
	 
	     public int compare(TreeNode m1, TreeNode m2) {
	        if (m1.getSortOrder()== m2.getSortOrder()) {
	              if(m1.getKey().compareTo(m2.getKey())>0){
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


	/**
	 * @return the sortOrder
	 */
	public int getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the hideCheckbox
	 */
	public boolean isHideCheckbox() {
		return hideCheckbox;
	}

	/**
	 * @param hideCheckbox the hideCheckbox to set
	 */
	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}

}
