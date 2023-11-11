package com.project.ecommerce.model;

public class Role {

	private String name;
	private String desc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Role(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
