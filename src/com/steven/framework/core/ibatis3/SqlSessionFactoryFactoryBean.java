package com.steven.framework.core.ibatis3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.Assert;
/**
 * 封装了ibatis3的SessionFactory工厂类
 * @author steven
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionFactoryFactoryBean implements FactoryBean,InitializingBean{
	/**
	 * logger
	 */
	Log logger = LogFactory.getLog(SqlSessionFactoryFactoryBean.class);
	/**
	 * ibatis配置文件
	 */
	private Resource configLocation;
	/**
	 * ibatis映射文件
	 */
	private Resource[] mapperLocations;
	/**
	 * 数据源
	 */
	private DataSource dataSource;
	/**
	 *是否事务Aware型数据源
	 */
	private boolean useTransactionAwareDataSource = true;
	
	/**
	 * ibatis SessionFactory
	 */
	SqlSessionFactory sqlSessionFactory;
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(configLocation,"configLocation must be not null");
		
		sqlSessionFactory = createSqlSessionFactory();
	}

	/**创建SessionFactory
	 * @return 创建的SessionFactory
	 * @throws IOException
	 */
    private SqlSessionFactory createSqlSessionFactory() throws IOException {
	//先读取ibatis的配置信息
	Reader reader = new InputStreamReader(getConfigLocation().getInputStream());
	try {
	    //根据配置信息创建sessionFactory
	    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
		    .build(reader);
	    
	    //对sessionFactroy进行后续配置加工
	    Configuration conf = sqlSessionFactory.getConfiguration();
	    if (dataSource != null) {
		DataSource dataSourceToUse = this.dataSource;
		if (this.useTransactionAwareDataSource
			&& !(this.dataSource instanceof TransactionAwareDataSourceProxy)) {
		    dataSourceToUse = new TransactionAwareDataSourceProxy(
			    this.dataSource);
		}

		conf.setEnvironment(new Environment("development",
			new ManagedTransactionFactory(), dataSourceToUse));
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(conf);
	    }

	    //添加映射信息
	    if (mapperLocations != null) {
		Map<String, XNode> sqlFragments = new HashMap<String, XNode>();
		for (Resource r : mapperLocations) {
		    logger.info("Loading iBatis3 mapper xml from file["
			    + r.getFile().getAbsolutePath() + "]");

		    Reader mapperReader = new InputStreamReader(r.getInputStream());
		    try {
			XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(mapperReader, conf, r.getFile().getAbsolutePath(), sqlFragments);
			mapperBuilder.parse();
		    } finally {
			mapperReader.close();
		    }
		}
	    }
	    return sqlSessionFactory;
	} finally {
	    reader.close();
	}
    }
	
	public Object getObject() throws Exception {
		return sqlSessionFactory;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Class getObjectType() {
		return SqlSessionFactory.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configurationFile) {
		this.configLocation = configurationFile;
	}

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

}
