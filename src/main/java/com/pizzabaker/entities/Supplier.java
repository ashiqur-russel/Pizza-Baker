package com.pizzabaker.entities;

import com.pizzabaker.utils.Utils;

public class Supplier implements Comparable<Supplier>{

	private long id;
	private String name;
	private String ingredients;
	private boolean hidden;
	
	public Supplier(long id, String name, String ingredients, boolean hidden) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.hidden = hidden;
	}
	public Supplier(Supplier supplier) {
		this.id = supplier.getId();
		if(supplier.getName() != null) {
			this.name = new String(supplier.getName());
		}
		if(supplier.getIngredients() != null) {
			this.ingredients = new String(supplier.getIngredients());
		}
		this.hidden = supplier.isHidden();
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
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
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
		Supplier other = (Supplier) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public int compareTo(Supplier o) {
		if(this.getName() == null) {
			if(o.getName() == null) {
				return 0;
			}else {
				return 1;
			}
		} else {
			if(o.getName() == null) {
				return -1;
			} else {
				return Utils.NormalizeText(this.getName()).compareTo(Utils.NormalizeText(o.getName()));
			}
		}
	}
}
