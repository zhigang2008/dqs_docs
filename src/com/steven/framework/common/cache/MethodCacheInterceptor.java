/**
 * MethodCacheInterceptor.java
 * Steven
 * 2011-5-6
 */
package com.steven.framework.common.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Steven
 * 
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {
	private final Log logger = LogFactory.getLog(getClass());

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public MethodCacheInterceptor() {
		super();
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result;

		logger.info("Find object from cache is " + cache.getName());

		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = cache.get(cacheKey);

		if (element == null) {
			logger.info("Can't find result in Cache , Get method result and create cache........!");
			result = invocation.proceed();
			element = new Element(cacheKey, (Serializable) result);
			cache.put(element);
		} else {
			logger.info("Find result in Cache , Get method result and create cache........!");
		}
		return element.getValue();
	}

	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	public void afterPropertiesSet() throws Exception {
		if (cache == null) {
			logger.error("Need a cache. Please use setCache(Cache) create it.");
		}
	}

}
