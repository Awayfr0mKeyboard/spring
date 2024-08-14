package com.bitc.vo;

public class ProductVO {
	
	private int num;
	private String name;
	private int price;
	
	public ProductVO() {
		System.out.println("Product 기본 생성자 호출");
	}
	
	public ProductVO(int num, String name, int price) {
		this.num = num;
		this.name = name;
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		System.out.println("setNum : " + num);
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName : " + name);
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		System.out.println("setPrice : " + price);
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", price=" + price + "]";
	}

	
	
}
