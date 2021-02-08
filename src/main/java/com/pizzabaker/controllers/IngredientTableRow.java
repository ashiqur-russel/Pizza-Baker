package com.pizzabaker.controllers;

public class IngredientTableRow {

	private long ingredientDetailId;
	private String name;
	private String province;
	private String supplier;
	private double price;
	private int quantity;
	private boolean hidden;
	public IngredientTableRow(long ingredientDetailId, String name, String province, String supplier, double price, int quantity, boolean hidden) {
		super();
		this.ingredientDetailId = ingredientDetailId;
		this.name = name;
		this.province = province;
		this.supplier = supplier;
		this.price = price;
		this.quantity = quantity;
		this.hidden = hidden;
	}
	public long getIngredientDetailId() {
		return ingredientDetailId;
	}
	public void setIngredientDetailId(long ingredientDetailId) {
		this.ingredientDetailId = ingredientDetailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
}
