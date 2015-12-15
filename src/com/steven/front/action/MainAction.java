package com.steven.front.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.core.GlobalSetting;
import com.steven.core.model.Module;
import com.steven.core.model.User;
import com.steven.core.service.UserManager;
import com.steven.framework.common.menu.Menu;
import com.steven.framework.common.menu.MenuUtil;
import com.steven.framework.common.menu.SimpleMenu;



public class MainAction extends com.steven.framework.base.BaseStruts2Action implements Preparable,SessionAware{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1962227911384381932L;

    /**
	 * 全局配置变量
	 */
    private GlobalSetting globalSetting=null;
	
    private Menu menu=null;
    private UserManager userManager=null;
    
      
    /*
	 * session 对象
	 */
	Map session=null;
	/**
	 * 过滤后的模块信息
	 */
	//TreeSet<Module> modules=new TreeSet<Module>();
	
    public String execute(){
	
	//判断是否已经初始化了用户菜单
	if(session.get(Constants.CURRENT_USER_MENUS)==null){
	    
	
	List<Module> allModules=null;
	
	//启用shiro获取当前用户
	User user=(User)SecurityUtils.getSubject().getPrincipal();
	    
	    
	//匿名用户,获取共用菜单
	if(user==null){
	   User anonymouseUser=userManager.getById(Integer.parseInt(globalSetting.getParam("ANONYMOUSE_USERID")));
	   allModules=(List<Module>)userManager.getUserModules(anonymouseUser);
	}
	//已登录用户
	else{
	    allModules=userManager.getUserModules(user);
	}
	
	if (allModules != null) {
	    //菜单格式化工具
	    MenuUtil util=new MenuUtil();
	    
		Iterator it = allModules.iterator();
		Module tempModule = null;
		int i=0;
		while (it.hasNext()) {
		   
		       SimpleMenu menu1=new SimpleMenu();
			tempModule = (Module) it.next();
			//log.debug("tempModule="+tempModule.toString());
			  menu1.setId(String.valueOf(tempModule.getModuleId()));
			  menu1.setName(tempModule.getModuleName());
			  menu1.setLink(tempModule.getUrl());
			  menu1.setSortOrder(tempModule.getSortOrder().intValue());
			  
			  if(tempModule.getParent()== 0 ){
			      //log.debug("setparent=root");
			    menu1.setParentId(MenuUtil.ROOTMENU);
			  }
			  else{
			     //log.debug("setparent=="+tempModule.getParent());
			      menu1.setParentId(String.valueOf(tempModule.getParent()));
			  }
			  //root.addChild(menu1);
			  util.addMenu(menu1);  
		}
		
	   this.setMenu(util.getRootMenu());
	   //放入session中
		session.put(Constants.CURRENT_USER_MENUS,this.menu);
		log.debug("setting session menu:"+this.menu);
	}
	 
	}//end if 初始化菜单判断
	
	return SUCCESS;
    }
       
    
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Map getSession() {
        return session;
    }
    public void setSession(Map session) {
        this.session = session;
    }
    @Override
    public void prepare() throws Exception {
	// TODO Auto-generated method stub
	
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }


    /**
     * @param globalSetting the globalSetting to set
     */
    public void setGlobalSetting(GlobalSetting globalSetting) {
        this.globalSetting = globalSetting;
    }
    

}
