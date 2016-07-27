package com.itaoniu.testserver.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public final static int UNKNOW = -1;
	
	public final static int ACCOUNT = 1;

	public final static int EMAIL = 2;
	
	public final static int MOBILE = 3;
	
	

	public static boolean equals(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		} else {
			return str1.equalsIgnoreCase(str2);
		}
	}

	public static boolean isEmpty(String str1) {
		if (str1 == null) {
			return true;
		}
		return str1.isEmpty();
	}

	public static String notNull(String str1) {
		if (str1 == null) {
			return "";
		}
		return str1;

	}

	public static boolean isNotBlank(String str1) {
		if (str1 == null || str1.isEmpty())
			return false;
		return true;
	}

	public static int type(String account) {
		if (isMobile(account)) {
			return MOBILE;

		} else if (isEmail(account)) {
			return EMAIL;

		} else if(isAccount(account)){
			return ACCOUNT;

		}else{
			return UNKNOW;
		}
	}

	public static boolean isMobile(String account) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(account);
		return m.matches();
	}

	public static boolean isEmail(String account) {
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(account);
		return m.matches();
	}
	
	public static boolean isAccount(String account){
		Pattern p = Pattern.compile("^[a-zA-Z]\\w{5,17}$");// 复杂匹配
		Matcher m = p.matcher(account);
		return m.matches();
	}
	
	public static String UUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return uuid;
	}
}
