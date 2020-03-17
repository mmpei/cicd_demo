package com.netease.cloud.skiff.demo.spring.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContextHolder {

	private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responses = new ThreadLocal<HttpServletResponse>();

	public static HttpServletRequest getRequest() {
		return requests.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requests.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responses.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responses.set(response);
	}

}