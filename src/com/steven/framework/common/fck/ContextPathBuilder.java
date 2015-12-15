/**
 * Classname:com.steven.framework.common.fck.ContextPathBuilder
 * Version info:V1.0
 * Date:2011-10-11 
 * Copyright notice: minshengLife
 */
package com.steven.framework.common.fck;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.impl.ServerRootPathBuilder;

/**改写FCKeditor的上传文件路径
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class ContextPathBuilder extends ServerRootPathBuilder {

    @Override
	public String getUserFilesPath(final HttpServletRequest request) {
	    return request.getContextPath().concat(super.getUserFilesPath(request));
	}
}
