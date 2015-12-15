package com.steven.framework.common.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class TreeFactory {
    
    protected Log log=LogFactory.getLog(getClass());
    /**
     * 所有节点项
     */
    private HashMap<String,TreeNode> treeNodeSet=new HashMap<String,TreeNode>(); 
    /**
     * 所有未找到父节点的节点
     */
    private HashMap<String,TreeNode> noParentTreeNodeSet=new HashMap<String,TreeNode>();
    
    /**
     * 待移除节点对象的临时列表
     */
    private List<String> tempRemoveList=new ArrayList<String>();
    
    /**
     * 默认根节点ID
     */
    public static String ROOTNODE="ROOTMENU";  
    /**
     * 是否存在根节点
     */
    private boolean hasSetRoot=false;
    
    /**获取根节点(包含先调整树形结构)
     * @return 根节点(包含所有子节点)
     */
    public TreeNode getRootTreeNode() {
	//先调整树形结构
	this.adjustTreeNodes();
        return treeNodeSet.get(ROOTNODE);
        
    }
    public void setRootTreeNode(TreeNode rootTreeNode) {
	  ROOTNODE=rootTreeNode.getKey();
	  this.treeNodeSet.put(ROOTNODE, rootTreeNode);
	  hasSetRoot=true;
    }
    
    /**添加节点
     * @param newTreeNode
     */
    public void addTreeNode(TreeNode newTreeNode){
    	System.out.println(newTreeNode.toString());
	if(!hasSetRoot){
	   TreeNode defaultRoot=new TreeNode();
	   defaultRoot.setKey(ROOTNODE);
	   defaultRoot.setTitle("根节点");
	   defaultRoot.setUrl("#");
	   defaultRoot.setFolder(true);
	   defaultRoot.setNoLink(true);
	   defaultRoot.setTooltip("根节点");
	   this.treeNodeSet.put(defaultRoot.getKey(), defaultRoot);
	   hasSetRoot=true;
	}
	
	 if(treeNodeSet.containsKey(newTreeNode.getParentId())){
		treeNodeSet.get(newTreeNode.getParentId()).addChild(newTreeNode);
		treeNodeSet.put(newTreeNode.getKey(), newTreeNode);
	  }else{
	      
	      //放入未找到父节点的节点组中
	      noParentTreeNodeSet.put(newTreeNode.getKey(), newTreeNode);
		
	  }
	
    }
    
    /**
     * 调整节点的树形结构
     */
    private void adjustTreeNodes(){
	int finalLoop=200;
	int loopnum=0;
	while(!noParentTreeNodeSet.isEmpty()){
	     
	    //先清空移除对象列表
	    tempRemoveList.clear();
	    
	    Iterator it=noParentTreeNodeSet.keySet().iterator();
	    String keyStr=null;
	    while(it.hasNext()){
		keyStr=(String)it.next();
		//log.debug("adust: "+keyStr);
		TreeNode tempTreeNode=this.noParentTreeNodeSet.get(keyStr);
		if(this.treeNodeSet.containsKey(String.valueOf(tempTreeNode.getParentId()))){
		    this.treeNodeSet.put(keyStr, tempTreeNode);
		    this.treeNodeSet.get(tempTreeNode.getParentId()).addChild(tempTreeNode);
		    tempRemoveList.add(keyStr);
		}
	    }
	    
	    //将已匹配对象从未匹配列表中移除
	    for(String removeKey:tempRemoveList){
		this.noParentTreeNodeSet.remove(removeKey);
	    }
	    
	   //如果持续循环仍不能解决树形结构处理,则把剩余的都放在根节点上
	  if(loopnum++ > finalLoop){
	      
	      Iterator it2=noParentTreeNodeSet.keySet().iterator();
		    String keyStr2=null;
		    while(it2.hasNext()){
			
			keyStr2=(String)it2.next();
			log.debug("final "+keyStr2);
			TreeNode tempTreeNode2=this.noParentTreeNodeSet.get(keyStr2);
			//放入根节点,并从noParentTreeNodeSet中移除
			this.treeNodeSet.get(ROOTNODE).addChild(tempTreeNode2);
			this.treeNodeSet.put(keyStr2, tempTreeNode2);
			
		    }
		//跳出循环    
               break;
	  }
	}
    }

}
