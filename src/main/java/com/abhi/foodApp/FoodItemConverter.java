package com.abhi.foodApp;

import java.io.IOException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.abhi.foodApp.model.FoodItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FoodItemConverter implements Converter<String, FoodItem>{
	
	private ObjectMapper objectMapper;
	
	public FoodItemConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public FoodItem convert(String source) {
		try {
			return objectMapper.readValue(source, FoodItem.class);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

}
