/**
 * 
 */
package com.vanstone.mongo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author shipeng
 */
public abstract class AbstractBaseMongoDAO<T> implements IBaseMongoDAO<T> {
	
	/**
	 * spring mongodb　集成操作类　
	 */
	protected MongoTemplate mongoTemplate;

	@Override
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	@Override
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	@Override
	public void update(Query query, Update update) {
		mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}

	@Override
	public T save(T entity) {
		mongoTemplate.insert(entity);
		return entity;
	}

	@Override
	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}
	
	@Override
	public T findById(String id, String collectionName) {
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}
	
	@Override
	public long count(Query query){
		return mongoTemplate.count(query, this.getEntityClass());
	}
	
	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	public Class<T> getEntityClass(){
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	@Override
	public void removeById(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException();
		}
		this.mongoTemplate.remove(new Query(Criteria.where(MongoConstant.DEFAULT_ID).is(id)), this.getEntityClass());
	}
	
	@Override
	public void removeById(String id, String collectionName) {
		if (collectionName == null || "".equals(collectionName)) {
			throw new IllegalArgumentException();
		}
		this.mongoTemplate.remove(new Query(Criteria.where(MongoConstant.DEFAULT_ID).is(id)), collectionName);
	}
	
	@Override
	public void removeAll(String collectionName) {
		if (collectionName == null || "".equals(collectionName)) {
			throw new IllegalArgumentException();
		}
		this.mongoTemplate.remove(new Query(), collectionName);
	}
	
	@Override
	public void dropCollection(String collectionName) {
		if (collectionName == null || "".equals(collectionName)) {
			throw new IllegalArgumentException();
		}
		this.mongoTemplate.dropCollection(collectionName);
	}
	
	@Override
	public void removeAll() {
		this.mongoTemplate.remove(new Query(), this.getEntityClass());
	}
	
	@Override
	public void dropCollection() {
		this.mongoTemplate.dropCollection(this.getEntityClass());
	}
	
	@Override
	public void removeByQuery(Query query) {
		this.mongoTemplate.remove(query, this.getEntityClass());
	}
	
	@Override
	public void removeByQuery(Query query, String collectionName) {
		this.mongoTemplate.remove(query, collectionName);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Collection findByQuery(Query query, long offset, long limit) {
		query.limit((int)limit);
		query.skip((int)offset);
		return this.mongoTemplate.find(query, this.getEntityClass());
	}

	@Override
	public long findTotalByQuery(Query query) {
		return this.mongoTemplate.count(query, this.getEntityClass());
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Collection findByQuery(Query query, String collectionName, long offset, long limit) {
		query.limit((int)limit);
		query.skip((int)offset);
		return this.mongoTemplate.find(query, this.getEntityClass(), collectionName);
	}
	
	@Override
	public long findTotalByQuery(Query query, String collectionName) {
		return this.mongoTemplate.count(query, collectionName);
	}
	
	/**
	 * 注入mongodbTemplate
	 * 
	 * @param mongoTemplate
	 */
	protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);
}
