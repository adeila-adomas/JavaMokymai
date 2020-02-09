package net.adeila.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaListReplaceTest {


	public static void main(String[] args) {
		System.out.println("Lambda..");
		
		ArrayList<String> list = new ArrayList<>();
		list.add("0");
		list.add("2");
		list.add("1");
		
		Stream<String> streamString = list.stream(); //.filter(c -> Integer.parseInt(c) > 0).findAny().map(c -> "no").orElse("yes");
		
		/*
		list = streamString
				.filter(c -> Integer.parseInt(c) > 0).map(c -> "yes")
				.collect(Collectors.toCollection(ArrayList::new));
		*/
		
		list = streamString
				.map(c -> Integer.parseInt(c) > 0 ? "yes" : "no")
				.collect(Collectors.toCollection(ArrayList::new));
		
		
		String[] s = list.toArray(new String[list.size()]);
		System.out.println(Arrays.toString(s));
	}

}
