/**
 * 
 */
package com.vanstone.mongo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 基础泛型MongoDAO方法
 * @author shipeng
 */
public interface IBaseMongoDAO<T> {
	
	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	List<T> find(Query query) ;
	
	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	T findOne(Query query) ;

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	void update(Query query, Update update) ;

	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param entity
	 * @return
	 */
	T save(T entity) ;

	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	T findById(String id) ;

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	T findById(String id, String collectionName) ;
	
	/**
	 * 求数据总和
	 * @param query
	 * @return
	 */
	long count(Query query);
	
	/**
	 * 通过Id删除
	 * @param id
	 */
	void removeById(String id);
	
	/**
	 * 通过Id删除
	 * @param id
	 * @param collectionName
	 */
	void removeById(String id, String collectionName);
	
	/**
	 * 通过Query删除
	 * @param query
	 */
	void removeByQuery(Query query);
	
	/**
	 * 通过Query删除
	 * @param query
	 * @param collectionName
	 */
	void removeByQuery(Query query, String collectionName);
	
	/**
	 * 物理删除全部数据
	 */
	void removeAll();
	
	/**
	 * 物理删除全部数据
	 * @param collectionName
	 */
	void removeAll(String collectionName);
	
	/**
	 * 删除当前集合,速度快
	 * @param collectionName
	 */
	void dropCollection(String collectionName);
	
	/**
	 * 删除当前集合，速度快
	 */
	void dropCollection();
	
	/**
	 * 分页检索
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Collection findByQuery(Query query, long offset, long limit);
	
	/**
	 * @param query
	 * @param collectionName
	 * @param offset
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Collection findByQuery(Query query, String collectionName, long offset, long limit);
	
	/**
	 * 检索数量
	 * @param query
	 * @return
	 */
	long findTotalByQuery(Query query);
	
	/**
	 * 检索数量
	 * @param query
	 * @param collectionName
	 * @return
	 */
	long findTotalByQuery(Query query, String collectionName);
}
