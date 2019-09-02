package com.moyan.example.j2se.filecopy;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;

/**
 * file infomation in procesing
 * @author Lenovo
 *
 */
public class FileProcessingInfo {
	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	private File file = null;
	private long size = 0;
	private long hasReadSize = 0;
	private long reMainedSize = 0;
	private float percent = 0;
	public static final int round = 3;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getHasReadSize() {
		return hasReadSize;
	}
	public void setHasReadSize(long hasReadSize) {
		this.hasReadSize = hasReadSize;
		this.reMainedSize = this.size - hasReadSize;
	}
	public long getReMainedSize() {
		return reMainedSize;
	}
	
	public float getPercent() {
		
		this.percent = (float)this.hasReadSize/this.size;
		BigDecimal   bigg = new BigDecimal(percent);
		return bigg.setScale(round, BigDecimal.ROUND_HALF_UP).floatValue();
	}
		
	public static void main(String[] args) {
		float f = (float)450057000/500687384;
		BigDecimal   bigg = new BigDecimal(f);
		float ff = bigg.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
		logger.info("" + f);
		logger.info("" + ff);
	}
}
