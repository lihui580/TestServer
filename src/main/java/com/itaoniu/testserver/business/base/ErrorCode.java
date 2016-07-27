package com.itaoniu.testserver.business.base;

import com.itaoniu.testserver.business.Template;

public abstract class ErrorCode {
	/**
	 * 打印错误码log
	 * @param type
	 * @return
	 */
	public static String printErrorCodeLog(StatusType type){
		return "ERROR["+type.getStatusCode()+"]"+type.getTemplate().getName()+":"+type.getReasonPhrase();
	}

	public interface StatusType {

		/**
		 * 获取状态码
		 * @return
		 */
		public int getStatusCode();

		/**
		 * 获取模板
		 * @return
		 */
		public Template getTemplate();

		/**
		 * 获取原因
		 * @return
		 */
		public String getReasonPhrase();

	}

}
