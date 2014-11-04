package com.vanstone.common.util.web;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <strong>PageInfo</strong><br>
 * <br> 
 * <strong>Create on : 2012-1-5<br></strong>
 * <p>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br></strong>
 * <p>
 * @author peng.shi peng.shi@ecointel.com.cn<br>
 * @version <strong>Ecointel v1.0.0</strong><br>
 */
public class PageInfo<T> {

	/**
	 * 当前页号
	 */
	private int pageNo;
	/**
	 * 每页大小
	 */
	private int pageSize;
	/**
	 * 总行数
	 */
	private long rows;
	/**
	 * 总页数
	 */
	private int pages;
	/**
	 * 区域大小
	 */
	private int rangeSize = 20;

	private Collection<T> objects;

	public int getPageNo() {
		if (this.pageNo <= 1) {
			return 1;
		}
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Collection<T> getObjects() {
		return objects;
	}
	
	/**
	 * 添加集合
	 * @param ts
	 */
	public void addObjects(Collection<T> ts) {
		if (ts == null || ts.size() <=0) {
			return;
		}
		_initObjColl();
		this.objects.addAll(ts);
	}
	
	/**
	 * 添加对象
	 * @param t
	 */
	public void addObject(T t) {
		_initObjColl();
		this.objects.add(t);
	}
	
	/**
	 * 清理对象
	 */
	public void clearObjects() {
		if (this.objects != null && this.objects.size() >0) {
			this.objects.clear();
		}
	}

	public int getNextPageNo() {
		if (this.pageNo >= this.getPages()) {
			return this.pageNo;
		}
		return this.pageNo + 1;
	}

	public int getPrePageNo() {
		if (this.pageNo <= 1) {
			return this.pageNo;
		}
		return this.pageNo - 1;
	}

	public int getRangeSize() {
		return rangeSize;
	}
	
	/**
	 * 设定区域大小
	 */
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public int getMiddle() {
		return this.rangeSize / 2;
	}

	/**
	 * 获取页面区域第一项
	 * 
	 * @return
	 */
	public int getRangeOfFirst() {
		if (this.getPages() <= this.getRangeSize()) {
			return 1;
		}

		if (this.getPageNo() - this.getMiddle() <= 1) {
			return 1;
		}

		return this.getPageNo() - this.getMiddle();
	}

	/**
	 * 获取页面区域最后一项
	 */
	public int getRangeOfEnd() {
		int end = this.getRangeOfFirst() - 1 + this.getRangeSize();
		if (end > this.getPages()) {
			return this.getPages();
		}
		return end;
	}

	private void _initObjColl() {
		if (this.objects == null) {
			this.objects = new ArrayList<T>();
		}
	}
	
	public static void main(String[] args) {
		for (int tmpPageNo = 1; tmpPageNo <= 100; tmpPageNo++) {
			PageUtil<Object> pu = new PageUtil<Object>(1001, tmpPageNo, 10);
			PageInfo<Object> pageInfo = pu.getPageInfo();
			pageInfo.setRangeSize(20);

			System.out.println("当前第 " + pageInfo.getPageNo() + " 页" + ";总页数：" + pageInfo.getPages() + "；" + "区域大小："
					+ pageInfo.getPageSize() + ";" + "区域中间值：" + pageInfo.getMiddle());

			int first = pageInfo.getRangeOfFirst();
			int end = pageInfo.getRangeOfEnd();
			for (int i = first; i <= end; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
