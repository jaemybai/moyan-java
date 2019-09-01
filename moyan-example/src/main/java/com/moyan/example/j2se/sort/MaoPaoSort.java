package com.moyan.example.j2se.sort;

import java.util.Arrays;

/**
 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，
 * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
 * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * @author Lenovo
 *
 */
public class MaoPaoSort {

	public static void main(String[] args) {

			sortFromFirst();
			System.out.println("=================");
			sortFromLast();
	}

	/**
	 * （未验证）这个效率没有下面那个高
	 */
	public static void sortFromFirst() {
		int[] src = new int[] { 1, 11, 2, 44, 5, 66, 8 };
//		 int score[] = {67, 69, 75, 87, 89, 90, 99, 100};
		System.out.println(Arrays.toString(src));
		for (int i = 0; i < src.length; i++) {

			for (int j = i + 1; j < src.length; j++) {
				// 倒序排序
				if (src[i] < src[j]) {
					int tem = src[i];
					src[i] = src[j];
					src[j] = tem;
				}
			}
		}
		System.out.println(Arrays.toString(src));
	}
	
	/**
	 * 冒泡排序
	 * 比较相邻的两个元素，如果第一个比第二个大就交换
	 */
	public static void sortFromLast() {
		int[] src = new int[] { 1, 11, 2, 44, 5, 66, 8 };

		System.out.println(Arrays.toString(src));
		for (int i = 0; i < src.length; i++) {

			for (int j = 0; j < src.length-i-1; j++) {
				// 从小到大排序
				if (src[j] > src[j+1]) {
					int tem = src[j];
					src[j] = src[j+1];
					src[j+1] = tem;
				}
			}
		}
		System.out.println(Arrays.toString(src));
	}
}
