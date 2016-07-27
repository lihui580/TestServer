package com.itaoniu.testserver.business.employees;

import com.itaoniu.testserver.business.Template;
import com.itaoniu.testserver.business.base.ErrorCode;

/**
 * 
 * @author lihui XXXErrorCode的使用： 
 * 1.拷贝当前文件 
 * 2.更改名字 
 * 3.修改template（模块）的值
 */
public class EmployeesErrorCode extends ErrorCode {

	private static final Template template = Template.EMPLOYEE;

	public enum Status implements StatusType {
		Code_001(1, "账号不存在"), 
		Code_002(2, "账号已存在"), 		
		;

		private final int code;
		private final String reason;
		private final Template template;

		Status(final int statusCode, final String reasonPhrase) {
			this.code = EmployeesErrorCode.template.getTemplateCode() + statusCode * Template.STATUS_MASK;
			this.reason = reasonPhrase;
			this.template = EmployeesErrorCode.template;
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
