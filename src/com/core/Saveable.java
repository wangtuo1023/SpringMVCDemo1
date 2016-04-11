package com.core;

/**
 * 可插入数据库的
 */
public interface Saveable {

	public String getTableName();
	// 主键字段
	public String[] getKeyColumns();
}
