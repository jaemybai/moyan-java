package com.moyan.example.j2se.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CompareTest {

	public static void main(String[] args) {
		Integer[] sorts = new Integer[]{1,42,2,null,10,53,null,9,75,98,null};
		List<Integer> list = Arrays.asList(sorts);
		Comparator<Integer> comp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 == null) {
					return 1;
				}
				if(o2 == null) {
					return -1;
				}
				return o2 - o1;
			}
		};
		
		Comparator<Long> comp2 = new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				if(o1 == null) {
					return 1;
				}
				if(o2 == null) {
					return -1;
				}
				long l = o2 - o1;
				return (int)l;
			}
		};
		System.out.println(comp2);
		System.out.println(Arrays.toString(sorts));
		System.out.println(list);
		Collections.sort(list, comp);
		System.out.println(list);
		
	}
}
