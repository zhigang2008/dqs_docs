/**
 * Classname:com.steven.framework.tag.MenuTag
 * Version info:V1.0
 * Date:2011-4-28 
 * Copyright notice: steven
 */
package com.steven.framework.tag;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.steven.framework.common.menu.Menu;

/**菜单展示标签</br>
 * <p>该标签用来实现系统菜单.有三种样式:横向下拉多级菜单.横向Navbar菜单.纵向菜单.
 * 该标签使用了Menu接口,简单的可以通过构造SimpleMenu实现.</p>
 * <p>前端页面展示使用了SuperFish菜单,需要引入superfish.js和superfish.css.菜单样式可以通过修改css文件实现</p>
 * @author Steven
 * @version 1.0
 * @since 1.0
 * @see com.steven.framework.common.menu.Menu
 * @see com.steven.framework.common.menu.MenuUtil
 * @see com.steven.framework.common.menu.SimpleMenu
 */
public class MenuTag extends SimpleTagSupport {
    
    /**
     * 需要展示菜单内容
     */
    private Menu menu;
  
    /**
     * 菜单栏对象ID
     */
    private String id="ul.menu";
    /**
     * 样式选择:1-横向下拉菜单(默认);2-横向Navbar菜单;3-纵向菜单;
     */
    private int style=1;
    /**
     * 需要显示的菜单级别.即要显示到第几级菜单
     */
    private int level=99;
    /**
     * 是否显示根节点菜单,默认不显示
     */
    private int showRoot=0;
    /**
     * 是否自动初始化菜单,默认初始化
     */
    private int autoInit=1;
    /**
     * 链接target
     */
    private String target="_self";
 
    
    /**
     * 页面路径
     */
    private String contentPath=null;
    
    private static int currentLevel=0;
    private static String currentStyle="sf-menu";
    
    private final static String styleNormal="sf-menu";
    private final static String styleNavbar="sf-navbar";
    private final static String styleVertical="sf-vertical";
    
    
    /**菜单样式
     * @return 样式类型
     */
    public int getStyle() {
        return style;
    }

    /**设置菜单样式
     * @param style 样式类型:1-横向下拉菜单(默认);2-横向Navbar菜单;3-纵向菜单;
     */
    public void setStyle(int style) {
        this.style = style;
    }
   
    /**显示层级
     * @return 显示层级
     */
    public int getLevel() {
        return level;
    }

    /**设置显示层级
     * @param level 显示层级
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    public void doTag() throws JspException, IOException {
	//恢复默认值
	currentLevel=0;
	currentStyle="sf-menu";
	
	//获取请求路径
	JspContext jspContext=getJspContext();
	
	HttpServletRequest request = (HttpServletRequest)((PageContext) jspContext).getRequest(); 
	this.contentPath=request.getContextPath();
	
	JspWriter out=jspContext.getOut();
	
	if(menu==null){
	    out.println("<!-- No menu items. -->");
	}else{
	    //初始化部分参数
	    switch(this.style){
	    case 1:
		currentStyle=styleNormal;
		break;
	    case 2:
		currentStyle=styleNormal+" "+styleNavbar;
		//navbar菜单限制只有两级
		//level=2;
		break;
	    case 3:
		currentStyle=styleNormal+" "+styleVertical;
		break;
	    default:
		currentStyle=styleNormal;  
	    }
	    //撰写菜单
	    out.println("<div id=\""+this.getId()+"\" class=\"menubox\">");
	    if(this.showRoot==1){
		writeRootMenu(out,menu);
	    }else{
	      writeMenu(out,menu.getChilds());
	    }
	    out.println("</div>");
	    
	    if(this.getAutoInit()==1){
	      out.println(this.printInitJSMenu());
	    }
	}
    }
    
    /**
     * 初始化菜单
     */
    private String printInitJSMenu() {
	StringBuffer initStr=new StringBuffer("<script>\n"); 
        initStr.append(" // initialise Superfish \n")
        .append("     $(document).ready(function(){ \n")
        .append("         $(\"ul.sf-menu\").superfish({\n")

        .append("          dropShadows:  false \n")
        .append("        });\n")
        .append("      }); \n")
        .append("</script>");
        
        return initStr.toString();
    }

    /**写出菜单内容
     * @param out JspWriter
     * @param menus 菜单列表
     * @throws IOException 
     */
    private void writeMenu(JspWriter out,Set<Menu> menus) throws IOException{
	
	if(currentLevel++ > level){
	    return;
	}
	
	if(menus!=null &&menus.size()>0){
	    if(style==2 && currentLevel >= 2){
		 out.println("<ul class=\""+styleNormal+"\">");
	    }else{
		out.println("<ul class=\""+currentStyle+"\">");
	    }
	    
	    String linkUrl="#";
	    for(Menu menu:menus){
	    	if(menu.getLink()!=null && !menu.getLink().equals("#") && !menu.getLink().equals("")){
	    		linkUrl=this.contentPath+"/"+menu.getLink();
	    	}
		out.println("<li><a href=\""+linkUrl+"\" target=\""+target+"\">"+menu.getName()+"</a>");
		writeMenu(out,menu.getChilds());
		out.println("</li>");
		//每调用一次子级就会影响层级计算,所以需要再消减回来.
		currentLevel--;
	    }
	    out.println("</ul>");
	}
	
    }

    /**从根节点开始写菜单
     * @param out      JspWriter
     * @param rootMenu 根节点菜单
     * @throws IOException 读写异常
     */
    private void writeRootMenu(JspWriter out,Menu rootMenu) throws IOException{
	 out.println("<ul class=\""+currentStyle+"\">");
	 out.println("<li><a href=\""+rootMenu.getLink()+"\" target=\""+target+"\">"+rootMenu.getName()+"</a>");
	 writeMenu(out,rootMenu.getChilds());
	 out.println("</li>");
	 out.println("</ul>");
	 
	 currentLevel++;
    }
    /**是否显示根节点
     * @return 是否显示
     */
    public int isShowRoot() {
        return showRoot;
    }

    /**设置是否显示根节点
     * @param showRoot 0-不显示;1-显示;
     */
    public void setShowRoot(int showRoot) {
        this.showRoot = showRoot;
    }

    /**获取菜单内容
     * @return 菜单
     */
    public Menu getMenu() {
        return menu;
    }

    /**设置菜单内容
     * @param menu 待显示的菜单内容
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAutoInit() {
        return autoInit;
    }

    public void setAutoInit(int autoInit) {
        this.autoInit = autoInit;
    }

	/**
	 * @return the contentPath
	 */
	public String getContentPath() {
		return contentPath;
	}

	/**
	 * @param contentPath the contentPath to set
	 */
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

 }
