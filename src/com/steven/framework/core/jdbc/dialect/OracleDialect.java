package com.steven.framework.core.jdbc.dialect;

/**针对oracle方言制定的物理分页实现
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class OracleDialect extends Dialect{
	
	/* (non-Javadoc)
	 * @see com.steven.framework.core.jdbc.dialect.Dialect#supportsLimit()
	 */
	public boolean supportsLimit() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.steven.framework.core.jdbc.dialect.Dialect#supportsLimitOffset()
	 */
	public boolean supportsLimitOffset() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.steven.framework.core.jdbc.dialect.Dialect#getLimitString(java.lang.String, int, java.lang.String, int, java.lang.String)
	 */
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
//			int end = offset+limit;
			String endString = offsetPlaceholder+"+"+limitPlaceholder;
			pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
		}
		else {
			pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}

}
