package com.moyan.example.j2se.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SortTest {

    private static Logger logger = LoggerFactory.getLogger(SortTest.class);

    public static void main(String[] args) {
        int[] array = new int[]{1,4,22,3,2,2,3,4,32,2,1,111,22,33,333,22,11};
        logger.info("" + array.length);
        logger.info(Arrays.toString(array));
        quickSort(array);
//        bubbleSort(array);
        logger.info("" + array.length);
        logger.info(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {

        int tem;
        for (int i=0;i<array.length;i++) {

            for(int j=0;j<array.length -1 -i;j++) {
                if(array[j] > array[j+1]) {
                    tem = array[j+1];
                    array[j+1] = array[j];
                    array[j] = tem;
                }
            }
        }
    }
    public static void quickSort(int[] array) {
        quickSort(array,0,array.length -1 );

    }

    public static void quickSort(int[] array, int start, int end) {
        if(start>= end) {
            return;
        }
        int middleIndex = getMiddleIndex(array,start,end);
        quickSort(array, start, middleIndex -1);
        quickSort(array,middleIndex + 1, end);
    }

    public static int getMiddleIndex(int[] array, int start, int end) {

        //取出参考值，位置空缺
        int firstCompare = array[start];
        while (start < end) {

            //首先和最右边，大于等于参考值的不变，取出小于参考值的
            while (start<end && firstCompare <= array[end]) {
                end--;
            }
            //将小于参考值的填充到空缺位处，当前end位置空缺
            array[start] = array[end];
            //然后和最左边，小于等于参考值的不变，取出大于参考值的
            while (start< end && firstCompare >= array[start]) {
                start++;
            }
            //将大于参考值的填充到空缺位处，当前start位置空缺
            array[end] = array[start];
        }
        //将初始参考值填充到最终start位置，
        array[start] = firstCompare;
        return start;

    }
}
