package com.steven.framework.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class UUIDGenerator  {
	public static String getID() {
		return StringUtils.remove(UUID.randomUUID().toString(), '-');
	}

	public static void main(String[] args) {
		UUID.randomUUID();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			UUID.randomUUID();
		}
		time = System.currentTimeMillis() - time;
		System.out.println(time);
	}
}
