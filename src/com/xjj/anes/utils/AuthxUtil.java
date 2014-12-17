package com.xjj.anes.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;

public class AuthxUtil {
	private static Log log = LogFactory.getLog("AuthxUtil");
	private static final String splitString = "~_~";

	private static String asciiToHex(String value) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			sb.append(Integer.toString((int) value.charAt(i), 16));
		}
		return sb.toString();
	}

	private static String hexToAscii(String value) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length(); i = i + 2) {
			sb.append((char) Integer.parseInt(value.substring(i, i + 2), 16));
		}
		return sb.toString();
	}

	public static String encryptByMd5(String value) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = value.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public static String getRandNumber(int n) {
		String rn = "";
		if (n > 0 && n < 10) {
			Random r = new Random();
			String str = "";
			for (int i = 0; i < n; i++) {
				str += "9";
			}
			int num = Integer.parseInt(str);
			while (rn.length() < n) {
				rn = "" + r.nextInt(num);
			}
		} else {
			rn = "0";
		}
		return rn;
	}

	public static String encryptRememberMe(List<String> list) {
		String rememberMe = "";
		for (String key : list) {
			if (rememberMe != "") {
				rememberMe += splitString;
			}
			rememberMe += key;
		}
		rememberMe = Base64.encodeBase64String(rememberMe.getBytes());
		rememberMe = AuthxUtil.asciiToHex(rememberMe);
		return rememberMe;
	}

	public static List<String> unencryptRememberMe(String rememberMe) {
		List<String> list = new ArrayList<String>();
		rememberMe = AuthxUtil.hexToAscii(rememberMe);
		byte[] bt = Base64.decodeBase64(rememberMe);
		rememberMe = new String(bt);
		String[] rm = rememberMe.split(splitString);
		for (String key : rm) {
			list.add(key);
		}
		return list;
	}

	public static void main(String[] args) throws Exception	{
		System.out.println(AuthxUtil.encryptByMd5("123456"));
		System.out.println(AuthxUtil.encryptByMd5("E10ADC3949BA59ABBE56E057F20F883E"));
	}

}
