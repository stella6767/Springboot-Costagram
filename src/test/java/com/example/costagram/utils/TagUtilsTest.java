package com.example.costagram.utils;

import org.junit.jupiter.api.Test;

public class TagUtilsTest {
	
	@Test
	public void 태그파싱_테스트() {
		String tags = "#만화 #도라에몽";

		String[] temp = tags.split("#");
		System.out.println(temp.length);
	}

}
