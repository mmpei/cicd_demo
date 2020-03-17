package com.netease.cloud.skiff.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.cloud.skiff.demo.spring.holder.WebContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

public class AbstractController {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

	private static final String UTF8_NAME="utf-8";
	
	protected int SUCCESS = 200;
	protected int FAIL = 400;
	protected int SERVER_FAIL = 500;

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	protected String commonReturn(int statusCode, String body, String contentType) {
		HttpServletResponse response = WebContextHolder.getResponse();
		response.setCharacterEncoding(UTF8_NAME);
		response.setContentType(contentType);

		response.setStatus(statusCode);

		try {
			response.getWriter().write(body);
		} catch (Exception e) {
			logger.warn("io exception.");
		}
		return null;
	}

	protected String commonReturnBytes(int statusCode, byte[] body, String contentType, Map<String, String> headers) {
		HttpServletResponse response = WebContextHolder.getResponse();
		response.setCharacterEncoding(UTF8_NAME);
		response.setContentType(contentType);
		response.setStatus(statusCode);
		if (headers != null && !headers.isEmpty()) {
			headers.forEach(response::setHeader);
		}

		try {
			response.getOutputStream().write(body);
		} catch (Exception e) {
			logger.warn("io exception.");
		}
		return null;
	}

	protected String commonReturn(int statusCode, String body) {
		return commonReturn(statusCode, body, MappingJackson2JsonView.DEFAULT_CONTENT_TYPE);
	}

	public String successReturn(Map<String, Object> results) {
		if (results == null) {
			results = Collections.EMPTY_MAP;
		}
		String body = JSON.toJSONString(results);

		return commonReturn(SUCCESS, body);
	}

	public String successReturn(int statusCode, Map<String, Object> results) {
		if (results == null) {
			results = Collections.EMPTY_MAP;
		}
		String body = JSON.toJSONString(results);
		return commonReturn(statusCode, body);
	}

	public String failReturn(int errorCode, String reason) {
		JSONObject bodyJSON = new JSONObject();
		bodyJSON.put("code", errorCode);
		bodyJSON.put("reason", reason);
		return commonReturn(errorCode, bodyJSON.toJSONString());
	}
}
