/**
 * 
 */
package com.vanstone.common.component.db;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.vanstone.common.util.StringUtils;


/**
 * 通用页面分页查询条件
 * 
 * @author liushy
 * 
 */
public class PagedQuery {

	/**
	 * 页面大小
	 */
	private int pageSize;// 页面大小

	/**
	 * 当前�?
	 */
	private int pageNumber;// 当前�?
	
	/**
	 * 页面个数
	 */
	private int pagesCount;

	/**
	 * 顺序
	 */
	private ListOrderedMap orders;

	/**
	 * 
	 */
	public PagedQuery() {
	}

	/**
	 * @return the pagesCount
	 */
	public final int getPagesCount() {
		return pagesCount;
	}


	/**
	 * @param pagesCount the pagesCount to set
	 */
	public final void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}


	/**
	 * @return the pageSize
	 */
	public final int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the orders
	 */
	public final boolean isHasOrder() {
		return orders == null && orders.size() > 0;
	}

	/**
	 * 返回排序的sql字符�?<br>
	 * 
	 * @return String .例如�?name desc,sex asc,description desc"
	 */
	@SuppressWarnings("unchecked")
	public final String getOrderClause() {
		if (orders != null && orders.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			List<Order> list = orders.asList();
			for (int i = 0; i < list.size() - 1; i++) {
				Order order = list.get(i);
				buffer.append(order.orderBy);
				if (!org.apache.commons.lang.StringUtils.isEmpty(order.order)) {
					buffer.append(' ');
					buffer.append(order.order);
				}
				buffer.append(',');
			}
			Order order = list.get(list.size() - 1);
			buffer.append(order.orderBy);
			if (!org.apache.commons.lang.StringUtils.isEmpty(order.order)) {
				buffer.append(' ');
				buffer.append(order.order);
			}
			return buffer.toString();
		} else {
			return null;
		}
	}

	public void clearOrders() {
		if (orders != null) {
			this.orders.clear();
		}
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public final void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNumber
	 */
	public final int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public final void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public final boolean appendOrder(String columnName, DatabaseOrder order) {
		if (StringUtils.isEmpty(columnName) || order == null) {
			return false;
		}
		if (orders == null) {
			orders = new ListOrderedMap();
		}
		if (orders.containsKey(columnName)) {
			((Order) orders.get(columnName)).order = order.name();
		} else {
			Order o = new Order();
			o.order = order.name();
			o.orderBy = columnName;
			orders.put(columnName, o);
		}
		return true;
	}

	private class Order {
		private String orderBy;
		private String order;
	}
}
