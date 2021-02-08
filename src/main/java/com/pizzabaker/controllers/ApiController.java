package com.pizzabaker.controllers;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzabaker.daos.IngredientDAO;
import com.pizzabaker.entities.Ingredient;
import com.pizzabaker.entities.IngredientDetail;

@RestController
public class ApiController {


	@GetMapping("/ingredientDetails")
	public String ingredientDetails(@RequestParam("id") long id) {
		try {
			Ingredient ingredient = new IngredientDAO().selectIngredientById(id);
			JSONArray jsonArray = new JSONArray();
			for(IngredientDetail detail : ingredient.getIngredientDetails()) {
				if(detail.isHidden() || detail.getQuantity() <= 0) continue;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", detail.getId());
				jsonObject.put("region", detail.getProvince());
				jsonObject.put("price", detail.getPrice());
				jsonArray.put(jsonObject);
			}
			return jsonArray.toString();
		    //return "[{\"id\":"+id+",\"region\":\"region1\",\"price\":1.2},{\"id\":2,\"region\":\"region2\",\"price\":1.4}]";
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}
}
