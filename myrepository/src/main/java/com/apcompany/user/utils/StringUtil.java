package com.apcompany.user.utils;

public class StringUtil {

	public static boolean isEmpty(String input) {

		if ("".equals(input) || input == null) {
			return true;
		}
		return false;

	}
}
