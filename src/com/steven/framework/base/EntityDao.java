package com.steven.framework.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**DAO接口
 * @author Steven
 * @version 1.0
 * @since 1.0
 * @param <E>
 * @param <PK>
 */
public interface EntityDao<E, PK extends Serializable> {

    /**根据ID查询
     * @param id 查询的ID值
     * @return 查询结果
     * @throws DataAccessException
     */
    public Object getById(PK id) throws DataAccessException;

    /**根据ID删除
     * @param id 删除对象的ID值
     * @throws DataAccessException
     */
    public void deleteById(PK id) throws DataAccessException;

    /** 插入数据 */
    public void save(E entity) throws DataAccessException;
    
    /** 插入数据  提供判断*/
    public int insert(E entity) throws DataAccessException;

    /** 更新数据 */
    public void update(E entity) throws DataAccessException;

    /** 根据id检查是否插入或是更新数据 */
    public void saveOrUpdate(E entity) throws DataAccessException;

    /**是否是唯一的数据
     * @param entity 检查对象
     * @param uniquePropertyNames 检查的唯一性属性
     * @return
     * @throws DataAccessException
     */
    public boolean isUnique(E entity, String uniquePropertyNames)
	    throws DataAccessException;

    /** 用于hibernate.flush() 有些dao实现不需要实现此类 */
    // public void flush() throws DataAccessException;

    /**列出所有数据
     * @return 所有数据列表
     * @throws DataAccessException
     */
    public List<E> findAll() throws DataAccessException;
    
    /**列出基础代码数据
     * @return 基础代码列表
     * @throws DataAccessException
     */
    public List<E> getBaseCode() throws DataAccessException;

}
