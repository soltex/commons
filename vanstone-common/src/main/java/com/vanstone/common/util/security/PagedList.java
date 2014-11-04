package com.vanstone.common.util.security;

import java.util.List;

public class PagedList<T> {
	public static final int DEFAULT_PAGE_SIZE = 15;

	protected List<T> items = null;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected int page = 1;
	protected int totalItems = 0;

	public PagedList(Integer totalItems, Integer pageSize, Integer page) {
		this(null, totalItems, pageSize, page);
	}

	public PagedList(List<T> items, Integer totalItems, Integer pageSize,
			Integer page) {
		setItems(items);
		setTotalItems(totalItems);
		setPageSize(pageSize);
		setPage(page);
	}

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * 获得总记录数
	 */
	public int getTotalItems() {
		return totalItems;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalItems(Integer totalItems) {
		if (totalItems == null || totalItems < 0) {
			totalItems = 0;
		}
		this.totalItems = totalItems;
	}

	/**
	 * 根据pageSize与totalItems计算总页数.
	 */
	public int getTotalPages() {
		float nrOfPages = (float) getTotalItems() / getPageSize();
		return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
				: nrOfPages);
	}

	public int getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page == null || page < 1) {
			page = 1;
		} else if ((page - 1) * pageSize > totalItems) {
			page = getTotalPages();
		}
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 是否最后一页.
	 */
	public boolean isLastPage() {
		return getPage() >= getTotalPages();
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return getPage();
		} else {
			return getPage() + 1;
		}
	}

	/**
	 * 是否第一页.
	 */
	public boolean isFirstPage() {
		return getPage() <= 1;
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrevPage() {
		if (isFirstPage()) {
			return getPage();
		} else {
			return getPage() - 1;
		}
	}
}
