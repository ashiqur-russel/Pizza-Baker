package com.pizzabaker.entities;

public class OrderIngredient {

	private String ingredientName;
	private IngredientDetail ingredientDetail;
	private int quantity;
	private double price;
	public OrderIngredient(String ingredientName, IngredientDetail ingredientDetail, int quantity, double price) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientDetail = ingredientDetail;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public IngredientDetail getIngredientDetail() {
		return ingredientDetail;
	}
	public void setIngredientDetail(IngredientDetail ingredientDetail) {
		this.ingredientDetail = ingredientDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
