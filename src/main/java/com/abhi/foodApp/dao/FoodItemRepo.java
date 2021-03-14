package com.abhi.foodApp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abhi.foodApp.model.FoodItem;

public interface FoodItemRepo extends MongoRepository<FoodItem, String>{
	
//	<S extends FoodItem> S saveOrUpdate(S entity) ;
}
