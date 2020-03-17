package com.netease.cloud.skiff.demo.spring.holder;

import org.junit.Test;

public class WebContextHolderTest {

	@Test
	public void testGetSet() {
		WebContextHolder.setRequest(null);
		WebContextHolder.setResponse(null);

		WebContextHolder.getRequest();
		WebContextHolder.getResponse();
	}
}