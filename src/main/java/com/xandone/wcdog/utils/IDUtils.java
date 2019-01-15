package com.xandone.wcdog.utils;

import java.util.Random;

public class IDUtils {

	/**
	 * id生成
	 */
	public static String RandomId() {
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		return str;
	}

}
