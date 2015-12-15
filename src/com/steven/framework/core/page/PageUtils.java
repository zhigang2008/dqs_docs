package com.steven.framework.core.page;

import java.util.List;

/**分页辅助工具类
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class PageUtils {
	
	private PageUtils(){}
	
	/**分页的第一个记录位置
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @return 第一个记录位置
	 */
	public static int getFirstResult(int pageNumber,int pageSize) {
		if(pageSize <= 0) throw new IllegalArgumentException("[pageSize] must great than zero");
		return (pageNumber - 1) * pageSize;
	}
	
	/**页面信息
	 * @param currentPageNumber
	 * @param lastPageNumber
	 * @param count
	 * @return
	 */
	public static List<Integer> generateLinkPageNumbers(int currentPageNumber,int lastPageNumber,int count) {
		int avg = count / 2;
		
		int startPageNumber = currentPageNumber - avg;
		if(startPageNumber <= 0) {
			startPageNumber = 1;
		}
		
		int endPageNumber = startPageNumber + count - 1;
		if(endPageNumber > lastPageNumber) {
			endPageNumber = lastPageNumber;
		}
		
		if(endPageNumber - startPageNumber < count) {
			startPageNumber = endPageNumber - count;
			if(startPageNumber <= 0 ) {
				startPageNumber = 1;
			}
		}
		
		java.util.List<Integer> result = new java.util.ArrayList();
		//添加最前面的页面
		if(startPageNumber>1){
			result.add(new Integer(1));
		}
		for(int i = startPageNumber; i <= endPageNumber; i++) {
			result.add(new Integer(i));
		}
		//添加最前和最末页码
		if(endPageNumber<lastPageNumber){
			result.add(lastPageNumber);
		}
		return result;
	}
	
	
	/**计算最后的页面
	 * @param totalElements 总记录数
	 * @param pageSize 每页记录数
	 * @return 最后页面
	 */
	public static int computeLastPageNumber(int totalElements,int pageSize) {
		int result = totalElements % pageSize == 0 ? 
				totalElements / pageSize 
				: totalElements / pageSize + 1;
		if(result <= 1)
			result = 1;
		return result;
	}
	
    /**计算页面数
     * @param pageNumber 
     * @param pageSize
     * @param totalElements
     * @return
     */
    public static int computePageNumber(int pageNumber, int pageSize,int totalElements) {
		if(pageNumber <= 1) {
			return 1;
		}
    	if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > computeLastPageNumber(totalElements,pageSize)) { //last page
			return computeLastPageNumber(totalElements,pageSize);
		}
		return pageNumber;
    }
}
