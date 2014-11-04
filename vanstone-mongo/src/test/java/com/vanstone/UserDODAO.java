/**
 * 
 */
package com.vanstone;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.vanstone.mongo.AbstractBaseMongoDAO;

/**
 * @author shipeng
 *
 */
public class UserDODAO extends AbstractBaseMongoDAO<UserDO> {

	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		
	}
	
}
