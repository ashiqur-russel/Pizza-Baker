package com.pizzabaker.entities;

import java.util.List;

public class Ingredient {

	private long id;
	private String name;
	private List<IngredientDetail> ingredientDetails;
	public Ingredient(long id, String name, List<IngredientDetail> ingredientDetails) {
		super();
		this.id = id;
		this.name = name;
		this.ingredientDetails = ingredientDetails;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<IngredientDetail> getIngredientDetails() {
		return ingredientDetails;
	}
	public void setIngredientDetails(List<IngredientDetail> ingredientDetails) {
		this.ingredientDetails = ingredientDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
