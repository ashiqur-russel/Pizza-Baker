package com.pizzabaker.entities;

public class IngredientDetail {

	private long id;
	private String province;
	private double price;
	private int quantity;
	private Supplier supplier;
	private boolean hidden;
	public IngredientDetail(long id, String province, double price, int quantity, Supplier supplier, boolean hidden) {
		super();
		this.id = id;
		this.province = province;
		this.price = price;
		this.quantity = quantity;
		this.supplier = supplier;
		this.hidden = hidden;
	}
	public IngredientDetail(IngredientDetail ingredientDetail) {
		this.id = ingredientDetail.getId();
		if(ingredientDetail.getProvince() != null) {
			this.province = new String(ingredientDetail.getProvince());
		}
		this.price = ingredientDetail.getPrice();
		this.quantity = ingredientDetail.getQuantity();
		if(ingredientDetail.getSupplier() != null) {
			this.supplier = new Supplier(ingredientDetail.getSupplier());
		}
		this.hidden = ingredientDetail.isHidden();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
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
		IngredientDetail other = (IngredientDetail) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
