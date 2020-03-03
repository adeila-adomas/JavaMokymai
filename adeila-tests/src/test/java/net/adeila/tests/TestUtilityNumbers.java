package net.adeila.tests;

import java.util.ArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestUtilityNumbers {

	@ParameterizedTest
	@ValueSource(ints = {1, 3, 6, 7, 2, 9})
	void testUtilityNumbers(int i) {
		System.out.println("Parameter: " + i);
	}
	
	static ArrayList<String> getTemplates() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("TEST");
		list.add("TEST2");
		list.add("TEST3");
		
		return list;
	}
	
	@ParameterizedTest
	@MethodSource("getTemplates")
	void testUtilityMethodSource(String s) {
		System.out.println("List parameter: " + s);
	}
}
