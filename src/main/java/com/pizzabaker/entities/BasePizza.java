package com.pizzabaker.entities;

public class BasePizza {

	private long id;
	private int size;
	private double price;
	public BasePizza(long id, int size, double price) {
		super();
		this.id = id;
		this.size = size;
		this.price = price;
	}
	public BasePizza(BasePizza basePizza) {
		this.id = basePizza.getId();
		this.size = basePizza.getSize();
		this.price = basePizza.getPrice();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
		BasePizza other = (BasePizza) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
