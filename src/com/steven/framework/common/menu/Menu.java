/**
 * Classname:com.steven.framework.common.menu.Menu
 * Version info:V1.0
 * Date:2011-4-26 
 * Copyright notice: steven
 */
package com.steven.framework.common.menu;

import java.io.Serializable;
import java.util.Set;

/**菜单接口
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public interface Menu extends Serializable {
    /** 菜单ID号
     * <p>为了便于通用,使用了String类型</p>
     * @return ID号
     */
    public String getId();
    /**菜单名称
     * @return 菜单名称
     */
    public String getName();
    /**菜单链接地址
     * @return 链接地址
     */
    public String getLink();
    /**下级菜单
     * @return 下级菜单列表
     */
    public Set<Menu> getChilds();
    /**获取父节点的ID
     * @return 父节点ID
     */
    public String getParentId();
    /**添加子节点
     * @param menu
     */
    public void addChild(Menu menu);
    /**排序字段
     * @return 排序
     */
    public int getSortOrder();
    /**设置排序字段
     * @param sortOrder
     */
    public void setSortOrder(int sortOrder);
    

}
