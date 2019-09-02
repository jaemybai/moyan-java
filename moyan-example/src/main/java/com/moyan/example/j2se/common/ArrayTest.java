package com.moyan.example.j2se.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;


public class ArrayTest {

	private static Logger logger = LoggerFactory.getLogger(ArrayTest.class);

	public static void main(String[] args) {
		Integer[] arrays = new Integer[]{1,2,3,4,5,11,9,2};
		
		logger.info(Arrays.toString(arrays));
		int count = 0;
		for(int i=0;i<arrays.length;i++) {
			
			for(int j=i+1;j<arrays.length;j++){
				//倒序
				count++;
				if(arrays[i]<arrays[j]) {
					int tem = arrays[i];
					arrays[i] =  arrays[j];
					arrays[j] = tem;
				}
			}
		}
		logger.info(Arrays.toString(arrays));
		logger.info("" + count);
		
		
		Arrays.sort(arrays, 
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						return 01 - o2;
					}
					
		});
		logger.info(Arrays.toString(arrays));

	}

	class MyComparator<T extends BaseDto> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			// TODO Auto-generated method stub
			return o1.getIndex()-o2.getIndex();
		}
		
	}
	
	class BaseDto {
		
		private int index ;

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		
	}
	
}
