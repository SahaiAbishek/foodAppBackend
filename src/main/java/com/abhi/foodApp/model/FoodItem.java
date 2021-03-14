package com.abhi.foodApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

public class FoodItem {
	@Id
	private String name;
	private String linkToYouTubeVideo;
	private String S3imageLocation;
	MealType Mealtype;// (Breakfast,Lunch, Dinner, Snacks)
	private String calories;
	private String description;
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkToYouTubeVideo() {
		return linkToYouTubeVideo;
	}

	public void setLinkToYouTubeVideo(String linkToYouTubeVideo) {
		this.linkToYouTubeVideo = linkToYouTubeVideo;
	}

	public String getS3imageLocation() {
		return S3imageLocation;
	}

	public void setS3imageLocation(String s3imageLocation) {
		S3imageLocation = s3imageLocation;
	}

	public MealType getMealtype() {
		return Mealtype;
	}

	public void setMealtype(MealType mealtype) {
		Mealtype = mealtype;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
