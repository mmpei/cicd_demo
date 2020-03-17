package com.netease.cloud.skiff.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static final String DEFAULT_ENCODING = "utf-8";
    //必须包含大写字符，大写字符，数字 长度为8-20
    private static final String REX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d~`!@#$%^&*_-]{8,20}+$";
    private static final Pattern DEFAULT_PATTERN = Pattern.compile(REX_PASSWORD);

    private static String IP_PORT_REG="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)((:[0-9]{1,5})|)";
    private static Pattern IP_PORT_PATTERN = Pattern.compile(IP_PORT_REG);

    /**
     * 默认正则检查密码串
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        return checkPassword(password, DEFAULT_PATTERN);
    }

    public static boolean checkHarborEndpoint(String endpoint) {
        if (StringUtils.isBlank(endpoint) || endpoint.length() > 128) {
            return false;
        }
        if (!endpoint.startsWith("http://") && !endpoint.startsWith("https://")) {
            return false;
        }
        return true;
    }

    /**
     * 自定义pattern检查密码串
     * @param password
     * @param pattern
     * @return
     */
    public static boolean checkPassword(String password, Pattern pattern) {
        if (StringUtils.isBlank(password) || pattern == null) {
            return false;
        }
        Matcher m = pattern.matcher(password);
        return m.find();
    }

	public static String genUniqueId() {
		return UUID.randomUUID().toString();
	}

    /**
     * 获取IP：PORT，如果无法获取，考虑是域名格式，就获取去除http://或者https://的部分
     * @param s
     * @return
     */
	public static String getIPPort(String s) {
	    if (StringUtils.isBlank(s)) {
	        return null;
        }
		Matcher m = IP_PORT_PATTERN.matcher(s);
		if (m.find()) {
			return m.group();
		}
		// 考虑是域名，返回域名：PORT的结果
        String ret = s;
        if (s.startsWith("http://")) {
            ret = s.substring("http://".length());
        } else if (s.startsWith("https://")) {
            ret = s.substring("https://".length());
        }
        if (ret.endsWith("/")) {
            ret = ret.substring(0, ret.length() - 1);
        }
		return ret;
	}
}
