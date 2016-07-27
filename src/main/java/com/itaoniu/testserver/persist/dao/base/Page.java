package com.itaoniu.testserver.persist.dao.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页显示类
 * 
 * @author yzhu
 * 
 */
public class Page<T> {

	public static final int DEFAULT_CURRRNT_PAGE = 1;

	public Page() {
		// TODO Auto-generated constructor stub
	}

	public Page(int maxResult) {
		this.maxResult = maxResult;
	}

	/** 每页显示记录数 **/
	public int maxResult = 20;
	
	/** 分页数据 **/
	private List<T> records=new ArrayList<T>();
	
	/** 总页数 **/
	private long totalpage = 0;

	/** 当前页 **/
	private int currentpage = 1;
	
	/** 总记录数 **/
	private long totalrecord = 0;

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}


}
