package com.abhi.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abhi.foodApp.dao.FoodItemRepo;
import com.abhi.foodApp.model.FoodItem;
import com.abhi.foodApp.model.MealType;
import com.abhi.foodApp.service.AmazonClientService;

@RestController
public class FoodAppRestController {

	@Autowired
	private FoodItemRepo repo;
	
	@Autowired
	private AmazonClientService amazonclient;

	@GetMapping(value = "/items")
	public ResponseEntity<List<FoodItem>> getAllFoodItems() {
		List<FoodItem> foodItems = repo.findAll();
		return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.OK);
	}

	@GetMapping(value = "/items/mealtype")
	public ResponseEntity<List<FoodItem>> getFoodItemsByMealType(@RequestParam(value = "mealType") MealType mealType) {
		return new ResponseEntity<List<FoodItem>>(HttpStatus.OK);
	}

	@GetMapping(value = "/items/randomItem")
	public ResponseEntity<List<FoodItem>> getRandomFoodItem(
			@RequestParam(value = "mealType", required = false) MealType mealType) {
		return new ResponseEntity<List<FoodItem>>(HttpStatus.OK);
	}

	@PostMapping(value = "/items", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> addFoodItem(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "foodItem", required = true) FoodItem foodItem) {
		FoodItem resp = repo.insert(foodItem);
		amazonclient.uploadFile(foodItem.getName(), file);
		if (resp != null)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/items")
	public ResponseEntity<Void> deleteAllEntries() {
		repo.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/items/Item")
	public ResponseEntity<Void> deleteById(@RequestParam String name) {
		try {
			amazonclient.deleteFileFromS3(name);
			repo.deleteById(name);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
