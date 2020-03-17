package com.netease.cloud.skiff.demo.utils;

import org.junit.Assert;
import org.junit.Test;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({CommonUtil.class})
public class CommonUtilTest {
    @Test
    public void testGetIPPort() {
        String result = CommonUtil.getIPPort("https://198.109.0.1:90/api/v2");
        Assert.assertEquals("198.109.0.1:90", result);
    }

    @Test
    public void testGetIPPort2() {
        String result = CommonUtil.getIPPort("http://198.109.0.1:90/api/v2");
        Assert.assertEquals("198.109.0.1:90", result);
    }

    @Test
    public void testGetIPPort3() {
        String result = CommonUtil.getIPPort("http://hub.c.163.com:90/");
        Assert.assertEquals("hub.c.163.com:90", result);
    }

    @Test
    public void testGenUniqueId() {
        String result = CommonUtil.genUniqueId();
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testCheckPassword() {
        boolean result = CommonUtil.checkPassword("112hhj");
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckPassword2() {
        boolean result = CommonUtil.checkPassword("112hhj_*D");
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckHarborEndpoint() {
        boolean result = CommonUtil.checkHarborEndpoint("hello world");
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckHarborEndpoint2() {
        boolean result = CommonUtil.checkHarborEndpoint("https://harbor.netease.com/");
        Assert.assertTrue(result);
    }
}