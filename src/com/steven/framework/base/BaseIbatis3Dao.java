package com.steven.framework.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import com.steven.framework.common.beanutils.PropertyUtils;
import com.steven.framework.core.page.Page;
import com.steven.framework.core.page.PageRequest;


/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 * @param <E>
 * @param <PK>
 */
public abstract class BaseIbatis3Dao<E,PK extends Serializable> extends DaoSupport implements EntityDao<E,PK> {
    /**
     * logger 子类可使用
     */
    protected final Log log = LogFactory.getLog(getClass());
    
    /**
     * ibatis SessionFactory
     */
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 内部实现类,sql操作模板
     */
    private SqlSessionTemplate sqlSessionTemplate;
	/* (non-Javadoc)
	 * @see org.springframework.dao.support.DaoSupport#checkDaoConfig()
	 */
	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull(sqlSessionFactory,"sqlSessionFactory must be not null");
	}

	/**获取SessionFactory
	 * @return sessionFactory实例
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**设置SessionFactory
	 * @param sqlSessionFactory
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	}

    /**获取SessionTemplate
     * @return
     */
    public SqlSessionTemplate getSqlSessionTemplate() {
    	return sqlSessionTemplate;
    }
    
    /* (non-Javadoc)
     * @see com.steven.framework.base.EntityDao#getById(java.io.Serializable)
     */
    public Object getById(PK primaryKey) {
	Object object = getSqlSessionTemplate().selectOne(
		getFindByPrimaryKeyStatement(), primaryKey);
	return object;
    }

    /* (non-Javadoc)
     * @see com.steven.framework.base.EntityDao#deleteById(java.io.Serializable)
     */
    public void deleteById(PK id) {
	int affectCount = getSqlSessionTemplate().delete(getDeleteStatement(),
		id);
    }

    /* (non-Javadoc)
     * @see com.steven.framework.base.EntityDao#save(java.lang.Object)
     */
    public void save(E entity) {
	prepareObjectForSaveOrUpdate(entity);
	int affectCount = getSqlSessionTemplate().insert(getInsertStatement(),
		entity);
	
    }
    
    @Override
	public int insert(E entity) throws DataAccessException {
	     prepareObjectForSaveOrUpdate(entity);
	     int affectCount = getSqlSessionTemplate().insert(getInsertStatement(),
		entity);
	    return affectCount;
	}
	
    
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#update(java.lang.Object)
	 */
	public void update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSessionTemplate().update(getUpdateStatement(), entity);
	}
	
	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * @param o
	 */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    public String getIbatisMapperNamesapce() {
        throw new RuntimeException("not yet implement");
    }
    
    public String getFindByPrimaryKeyStatement() {
        return getIbatisMapperNamesapce()+".getById";
    }

    public String getInsertStatement() {
        return getIbatisMapperNamesapce()+".insert";
    }

    public String getUpdateStatement() {
    	return getIbatisMapperNamesapce()+".update";
    }

    public String getDeleteStatement() {
    	return getIbatisMapperNamesapce()+".delete";
    }
    
    public String getFindAllStatement() {
    	return getIbatisMapperNamesapce()+".findAll";
    }
    public String getBaseCodeStatement() {
    	return getIbatisMapperNamesapce()+".baseCode";
    }
    public String getCountStatementForPaging(String statementName) {
		return statementName +".count";
   }
    
    /**分页查询,供内部及子类使用
     * @param statementName 映射文件中的查询语句名
     * @param pageRequest   分页参数信息
     * @return 分页查询结果
     */
    protected Page pageQuery(String statementName, PageRequest pageRequest) {
	return pageQuery(getSqlSessionTemplate(), statementName,
		getCountStatementForPaging(statementName), pageRequest);
    }

    /**分页查询
     * @param sqlSessionTemplate sql模板
     * @param statementName 执行语句
     * @param countStatementName 总数计数语句
     * @param pageRequest 分页查询参数
     * @return 分页查询结果
     */
    public static Page pageQuery(SqlSessionTemplate sqlSessionTemplate,
	    String statementName, String countStatementName,
	    PageRequest pageRequest) {

	//先计算总数
	Number totalCount = (Number) sqlSessionTemplate.selectOne(
		countStatementName, pageRequest);
	if (totalCount == null || totalCount.longValue() <= 0) {
	    return new Page(pageRequest, 0);
	}

	Page page = new Page(pageRequest, totalCount.intValue());
	

	// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
	// 与getSqlMapClientTemplate().queryForList(statementName,
	// parameterObject)配合使用
	Map filters = new HashMap();
	filters.put("offset", page.getFirstResult());
	filters.put("pageSize", page.getPageSize());
	filters.put("lastRows", page.getFirstResult() + page.getPageSize());
	filters.put("sortColumns", pageRequest.getSortColumns());

	Map parameterObject = PropertyUtils.describe(pageRequest);
	filters.putAll(parameterObject);

	List list = sqlSessionTemplate.selectList(statementName, filters, page
		.getFirstResult(), page.getPageSize());
	page.setResult(list);
	return page;
    }
	
	/* 仅提供给少量数据的全查询
	 * 
	 */
	public List findAll() {
		//throw new UnsupportedOperationException();
	    return sqlSessionTemplate.selectList(getFindAllStatement(), null, 0,1000);
	}
	
	/* 仅提供给少量数据的全查询
	 * 
	 */
	public List getBaseCode() {
		//throw new UnsupportedOperationException();
	    return sqlSessionTemplate.selectList(getBaseCodeStatement(), null, 0,1000);
	}
	

	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#isUnique(java.lang.Object, java.lang.String)
	 */
	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
	
	
	/**内部封装了Ibatis SessionFactory的模板操作类
	 * @author Steven
	 * @version 1.0
	 * @since 1.0
	 */
	public static class SqlSessionTemplate {
		/**
		 * Ibatis SessionFactory工厂类
		 */
		SqlSessionFactory sqlSessionFactory;
		
		/**初始化函数
		 * @param sqlSessionFactory
		 */
		public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			this.sqlSessionFactory = sqlSessionFactory;
		}

		/**通用的执行类.每个操作都使用一个session
		 * @param action 数据库操作
		 * @return 相应的操作结果
		 */
		public Object execute(SqlSessionCallback action)  {
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				Object result = action.doInSession(session);
				return result;
			}finally {
				if(session != null) session.close();
			}
		}
		
		/**单一查询,对应ibatis的selectOne
		 * @param statement 执行语句
		 * @param parameter 参数实体
		 * @return 结果
		 */
		public Object selectOne(final String statement,final Object parameter) {
			return execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectOne(statement, parameter);
				}
			});
		}
		
		/**列表查询(限定范围)
		 * @param statement 执行语句
		 * @param parameter 参数实体
		 * @param offset 移动量
		 * @param limit 限制返回的记录数
		 * @return 查询结果
		 */
		public List selectList(final String statement,final Object paramEntity,final int offset,final int limit) {
			return (List)execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, paramEntity, new RowBounds(offset,limit));
				}
			});
		}

		/**列表查询
		 * @param statement 执行语句 
		 * @param paramEntity 参数实体
		 * @return 查询结果
		 */
		public List selectList(final String statement,final Object paramEntity) {
			return (List)execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, paramEntity);
				}
			});
		}
		
		/**删除记录
		 * @param statement 执行语句
		 * @param paramEntity 参数实体
		 * @return 执行结果
		 */
		public int delete(final String statement,final Object paramEntity) {
			return (Integer)execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.delete(statement, paramEntity);
				}
			});
		}
		
		/**更新记录
		 * @param statement 执行语句
		 * @param paramEntity 参数实体
		 * @return 执行结果
		 */
		public int update(final String statement,final Object paramEntity) {
			return (Integer)execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.update(statement, paramEntity);
				}
			});
		}
		
		/**添加记录
		 * @param statement 执行语句
		 * @param paramEntity 参数实体
		 * @return 执行结果
		 */
		public int insert(final String statement,final Object paramEntity) {
			return (Integer)execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
				    
					return session.insert(statement, paramEntity);
				}
			});
		}
		
		
	} 
	
	/**数据操作的回调函数接口
	 * @author Steven
	 * @version 1.0
	 * @since 1.0
	 */
	public static interface SqlSessionCallback {
		
		/**在ibatis session中执行的内容
		 * @param session
		 * @return
		 */
		public Object doInSession(SqlSession session);
		
	}

	
	
}
