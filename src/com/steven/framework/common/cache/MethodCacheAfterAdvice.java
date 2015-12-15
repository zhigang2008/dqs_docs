/**
 * MethodCacheAfterAdvice.java
 * Steven
 * 2011-5-6
 */
package com.steven.framework.common.cache;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Steven
 * 
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice,
		InitializingBean {

	private final Log logger = LogFactory.getLog(getClass());

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public MethodCacheAfterAdvice() {
		super();
	}

	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object classObject) throws Throwable {
		String className = classObject.getClass().getName();
		List list = cache.getKeys();
		for (int i = 0; i < list.size(); i++) {
			String cacheKey = String.valueOf(list.get(i));
			if (cacheKey.contains(className)) {
				cache.remove(cacheKey);
				logger.debug("remove cache " + cacheKey);
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (cache == null) {
			logger.error("Need a cache. Please use setCache(Cache) create it.");
		}
	}

}
