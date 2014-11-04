package com.vanstone.common.util.web;

import com.vanstone.common.CommonConstants;

/**
 * <strong>PageBreakUtil</strong><br>
 * 页面分页工具类<br>
 * <strong>Create on : 2012-1-5<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br>
 * </strong>
 * <p>
 * 
 * @author peng.shi peng.shi@ecointel.com.cn<br>
 * @version <strong>Ecointel v1.0.0</strong><br>
 */
public class PageUtil<T> {

	private int pageNo;
	private int size = CommonConstants.DEFAULT_PAGE_SIZE;
	private int offset;
	private int pages;
	private int rows;
	
	/**
	 * 构造函数
	 * @param rows 总行数
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 */
	public PageUtil(int rows, int pageNo, int size) {
		if (size <= 0) {
			this.size = 10;
		} else {
			this.size = size;
		}

		if (pageNo <= 0) {
			pageNo = 1;
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}

		if (rows <= 0) {
			this.pageNo = 1;
			this.pages = 1;
			this.offset = 0;
			this.rows = 0;
			return;
		}

		this.rows = rows;
		int tmp = rows % this.size;

		if (tmp == 0) {
			this.pages = rows / this.size;
		} else {
			this.pages = rows / this.size + 1;
		}
		if (pageNo > pages) {
			this.pageNo = pages;
		}
		this.offset = (this.pageNo - 1) * size;
	}

	/**
	 * 返回当前页
	 * @return Returns the pageNo.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 获取当前
	 * @return Returns the pageSize.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 获取数据库第几项偏移量
	 * @return Returns the offset.
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * 获取总页数
	 * @return Returns the pages.
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * 获取行数
	 * @return Returns the rows.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * 返回页面VO对象
	 * @return
	 */
	public PageInfo<T> getPageInfo() {
		PageInfo<T> info = new PageInfo<T>();
		info.setPages(this.getPages());
		info.setRows(this.getRows());
		info.setPageNo(this.getPageNo());
		info.setPageSize(this.getSize());
		return info;
	}
}
