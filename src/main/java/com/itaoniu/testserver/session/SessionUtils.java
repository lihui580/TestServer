package com.itaoniu.testserver.session;

import javax.servlet.http.Cookie;

import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.StringUtils;

public class SessionUtils {

	public static SessionProxy obtainSession() {
		// 1.获取http头
		String session = getRequestSession();
		// 有session,更新session时间
		SessionProxy proxy = SessionProxy.get(session);
		if (proxy != null) {
			SessionProxy.expire(session);
			
		} else {
			proxy = new SessionProxy();
			SessionProxy.put(proxy);
			Cookie cookie = new Cookie("SESSION", proxy.getId());
			CxfUtils.getHttpServletResponse().addCookie(cookie);
			
		}

		return proxy;
	}

	private static String getRequestSession() {
		Cookie[] cookies = CxfUtils.getHttpServletRequest().getCookies();

		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (StringUtils.equals("SESSION", cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}


}
