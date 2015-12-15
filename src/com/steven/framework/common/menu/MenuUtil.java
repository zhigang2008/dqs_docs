/**
 * Classname:com.steven.framework.common.menu.MenuUtil
 * Version info:V1.0
 * Date:2011-4-26 
 * Copyright notice: steven
 */
package com.steven.framework.common.menu;

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
public class MenuUtil {
    
    protected Log log=LogFactory.getLog(getClass());
    /**
     * 所有菜单项
     */
    private HashMap<String,Menu> menuSet=new HashMap<String,Menu>(); 
    /**
     * 所有未找到父节点的菜单
     */
    private HashMap<String,Menu> noParentMenuSet=new HashMap<String,Menu>();
    
    /**
     * 待移除节点对象的临时列表
     */
    private List<String> tempRemoveList=new ArrayList<String>();
    
    /**
     * 默认根节点ID
     */
    public static String ROOTMENU="ROOTMENU";  
    /**
     * 是否存在根节点
     */
    private boolean hasSetRoot=false;
    
    /**获取根节点(包含先调整树形结构)
     * @return 根节点(包含所有子节点)
     */
    public Menu getRootMenu() {
	//先调整树形结构
	this.adjustMenus();
        return menuSet.get(ROOTMENU);
        
    }
    public void setRootMenu(Menu rootMenu) {
	  ROOTMENU=rootMenu.getId();
	  this.menuSet.put(ROOTMENU, rootMenu);
	  hasSetRoot=true;
    }
    
    /**添加菜单
     * @param newMenu
     */
    public void addMenu(Menu newMenu){
	if(!hasSetRoot){
	   SimpleMenu defaultRoot=new SimpleMenu();
	   defaultRoot.setId(ROOTMENU);
	   defaultRoot.setName("根目录");
	   defaultRoot.setLink("#");
	   this.menuSet.put(defaultRoot.getId(), defaultRoot);
	   hasSetRoot=true;
	}
	
	 if(menuSet.containsKey(newMenu.getParentId())){
		menuSet.get(newMenu.getParentId()).addChild(newMenu);
		menuSet.put(newMenu.getId(), newMenu);
	  }else{
	      
	      //放入未找到父节点的菜单组中
	      noParentMenuSet.put(newMenu.getId(), newMenu);
		
	  }
	
    }
    
    /**
     * 调整菜单的树形结构
     */
    private void adjustMenus(){
	int finalLoop=200;
	int loopnum=0;
	while(!noParentMenuSet.isEmpty()){
	     
	    //先清空移除对象列表
	    tempRemoveList.clear();
	    
	    Iterator it=noParentMenuSet.keySet().iterator();
	    String keyStr=null;
	    while(it.hasNext()){
		keyStr=(String)it.next();
		//log.debug("adust: "+keyStr);
		Menu tempMenu=this.noParentMenuSet.get(keyStr);
		if(this.menuSet.containsKey(String.valueOf(tempMenu.getParentId()))){
		    this.menuSet.put(keyStr, tempMenu);
		    this.menuSet.get(tempMenu.getParentId()).addChild(tempMenu);
		    tempRemoveList.add(keyStr);
		}
	    }
	    
	    //将已匹配对象从未匹配列表中移除
	    for(String removeKey:tempRemoveList){
		this.noParentMenuSet.remove(removeKey);
	    }
	    
	   //如果持续循环仍不能解决树形结构处理,则把剩余的都放在根节点上
	  if(loopnum++ > finalLoop){
	      
	      Iterator it2=noParentMenuSet.keySet().iterator();
		    String keyStr2=null;
		    while(it2.hasNext()){
			
			keyStr2=(String)it2.next();
			log.debug("final "+keyStr2);
			Menu tempMenu2=this.noParentMenuSet.get(keyStr2);
			//放入根节点,并从noParentMenuSet中移除
			this.menuSet.get(ROOTMENU).addChild(tempMenu2);
			this.menuSet.put(keyStr2, tempMenu2);
			
		    }
		//跳出循环    
               break;
	  }
	}
    }

}
