package com.itaoniu.testserver.aspect;

import com.itaoniu.testserver.business.Template;
import com.itaoniu.testserver.business.base.ErrorCode;

/**
 * 
 * @author lihui XXXErrorCode的使用： 
 * 1.拷贝当前文件 
 * 2.更改名字 
 * 3.修改template（模块）的值
 */
public class AspectErrorCode extends ErrorCode {

	private static final Template template = Template.SYSTEM;

	public enum Status implements StatusType {
		Code_001(1, "令牌失效"), 
		Code_002(1, "账号未登录"), 
		Code_003(3, "没有权限"), 
		;

		private final int code;
		private final String reason;
		private final Template template;

		Status(final int statusCode, final String reasonPhrase) {
			this.code = AspectErrorCode.template.getTemplateCode() + statusCode * Template.STATUS_MASK;
			this.reason = reasonPhrase;
			this.template = AspectErrorCode.template;
		}

		@Override
		public int getStatusCode() {
			return code;
		}

		@Override
		public Template getTemplate() {
			return template;
		}

		@Override
		public String getReasonPhrase() {
			return reason;
		}

		@Override
		public String toString() {
			return reason;
		}

	}

}
