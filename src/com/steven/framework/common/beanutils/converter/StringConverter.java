package com.steven.framework.common.beanutils.converter;
import org.apache.commons.beanutils.Converter;

/**字符串转换类,将空字符串转换为空值
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public final class StringConverter implements Converter {

	public StringConverter() {
	}

	public Object convert(Class type, Object value) {
		if (value == null || "".equals(value.toString())) {
			return (String) null;
		} else {
			return value.toString();
		}
	}
}

