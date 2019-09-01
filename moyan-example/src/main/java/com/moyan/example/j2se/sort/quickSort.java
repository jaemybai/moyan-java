package com.moyan.example.j2se.sort;

import java.util.Arrays;

/**
 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,
 * 一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
 * 然后再用同样的方法递归地排序划分的两部分。
 * @author Lenovo
 *
 */
public class quickSort {

	int a[] = { 49, 38, 65, 97, 76, 13, 27, 49};

	public static void main(String[] args) {
		new quickSort();
	}
	public quickSort() {

		System.out.println(Arrays.toString(a));
		quick(a);
		System.out.println("------------------");
		System.out.println(Arrays.toString(a));

	}

	public int getMiddle(int[] list, int low, int high) {

		int tmp = list[low]; // 数组的第一个作为中轴

		while (low < high) {

			//从high开始比较知道找到小于tem的位置元素
			while (low < high && list[high] >= tmp) {

				high--;

			}

			//将上述的high赋给list[low]
			list[low] = list[high]; // 比中轴小的记录移到低端

			//从low开始比较知道找到大于tem的位置元素
			while (low < high && list[low] <= tmp) {

				low++;

			}

			list[high] = list[low]; // 比中轴大的记录移到高端

		}

		list[low] = tmp; // 中轴记录到尾

		return low; // 返回中轴的位置

	}

	public void _quickSort(int[] list, int low, int high) {

		if (low < high) {

			int middle = getMiddle(list, low, high); // 将list数组进行一分为二

			_quickSort(list, low, middle - 1); // 对低字表进行递归排序

			_quickSort(list, middle + 1, high); // 对高字表进行递归排序

		}

	}

	public void quick(int[] a2) {

		if (a2.length > 0) { // 查看数组是否为空

			_quickSort(a2, 0, a2.length - 1);

		}

	}

}
