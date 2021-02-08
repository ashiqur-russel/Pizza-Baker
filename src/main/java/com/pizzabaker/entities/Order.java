package com.pizzabaker.entities;

import java.util.Date;
import java.util.List;

public class Order {

	private long id;
	private Date datetime;
	private BasePizza basePizza;
	private List<OrderIngredient> ingredients;
	public Order(long id, Date datetime, BasePizza basePizza, List<OrderIngredient> ingredients) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.basePizza = basePizza;
		this.ingredients = ingredients;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public BasePizza getBasePizza() {
		return basePizza;
	}
	public void setBasePizza(BasePizza basePizza) {
		this.basePizza = basePizza;
	}
	public List<OrderIngredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<OrderIngredient> ingredients) {
		this.ingredients = ingredients;
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
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public double getTotalPrice() {
		double ret = this.getBasePizza().getPrice();
		if(this.getIngredients() != null) {
			for(OrderIngredient ingredient : this.getIngredients()) {
				ret += ingredient.getPrice()*(double)ingredient.getQuantity();
			}
		}
		return ret;
	}
}
