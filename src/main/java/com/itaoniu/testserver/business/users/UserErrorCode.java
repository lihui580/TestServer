package com.itaoniu.testserver.business.users;

import com.itaoniu.testserver.business.Template;
import com.itaoniu.testserver.business.base.ErrorCode;

/**
 * 
 * @author lihui XXXErrorCode的使用： 
 * 1.拷贝当前文件 
 * 2.更改名字 
 * 3.修改template（模块）的值
 */
public class UserErrorCode extends ErrorCode {

	private static final Template template = Template.USER;

	public enum Status implements StatusType {
		Code_001(1, "账号不存在"), 
		Code_002(2, "账号/密码不能为空"), 
		Code_003(3, "头像不能为空"), 
		Code_004(4, "昵称不能为空"), 
		Code_005(5, "签名不能为空"), 
		Code_006(6, "账号已存在"), 
		Code_007(7, "UUID不存在"), 
		Code_008(8, "密码不正确"), 
		Code_009(9, "账号信息不存在"), 
		;

		private final int code;
		private final String reason;
		private final Template template;

		Status(final int statusCode, final String reasonPhrase) {
			this.code = UserErrorCode.template.getTemplateCode() + statusCode * Template.STATUS_MASK;
			this.reason = reasonPhrase;
			this.template = UserErrorCode.template;
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
