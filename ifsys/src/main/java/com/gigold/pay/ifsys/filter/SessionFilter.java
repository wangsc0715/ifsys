package com.gigold.pay.ifsys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Domain;

/**
 * Servlet Filter implementation class SessionFilter
 * 拦截所有的html请求
 */
public class SessionFilter extends Domain implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] allowUrls;

	/**
	 * @return the allowUrls
	 */
	public String[] getAllowUrls() {
		return allowUrls;
	}

	/**
	 * @param allowUrls
	 *            the allowUrls to set
	 */
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		boolean flag = false;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestUrl = httpRequest.getRequestURI().replace(httpRequest.getContextPath(), "");
		debug("===" + requestUrl);
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					flag = true;
				}
			}
		if (flag) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = httpRequest.getSession();
			if (session.getAttribute(SystemPropertyConfigure.getLoginKey()) == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String urls = fConfig.getInitParameter("allowUrlStr");
		this.allowUrls = urls.split(",");
	}

}
