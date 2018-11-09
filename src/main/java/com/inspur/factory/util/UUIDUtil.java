package com.inspur.factory.util;

import java.util.UUID;

/**
* @ClassName: UUIDUtil
* @Description:
*/
public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
