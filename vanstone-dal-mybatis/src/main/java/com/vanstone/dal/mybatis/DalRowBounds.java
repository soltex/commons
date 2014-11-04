package com.vanstone.dal.mybatis;

import org.apache.ibatis.session.RowBounds;


/**
 * <strong>Title : DalRowBounds </strong>. <br>
 * <strong>Description : 类的描述信息.</strong> <br>
 * <strong>Create on : 2011-7-20 上午11:23:13 </strong>. <br>
 */
public class DalRowBounds extends RowBounds {

	private int extOffset;
	private int extLimit;
	private int total;

	public DalRowBounds(int offset, int limit) {
		this.extOffset = offset;
		this.extLimit = limit;
	}

	public int getExtOffset() {
		return extOffset;
	}

	public int getExtLimit() {
		return extLimit;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
}
