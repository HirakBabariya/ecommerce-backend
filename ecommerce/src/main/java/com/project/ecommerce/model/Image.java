package com.project.ecommerce.model;

public class Image {

	private Long id;
	private String name;
	private String type;
	private byte[] picBytes;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(String name, String type, byte[] picBytes) {
		super();
		this.name = name;
		this.type = type;
		this.picBytes = picBytes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicBytes() {
		return picBytes;
	}

	public void setPicBytes(byte[] picBytes) {
		this.picBytes = picBytes;
	}

}
