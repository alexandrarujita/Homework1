package model;

import java.util.ArrayList;

import BLL.OrderBLL;

public class Order {
	
	private int id;
	private Customer c;
	private ArrayList<Product> prodList= new ArrayList<Product>();
	private double totalPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Product> getProdList() {
		return prodList;
	}
	
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public void addProd(Product p){
		prodList.add(p);
	}
	
	public void removeProd(Product p){
		//new OrderBLL().remove(p);
		prodList.remove(p);
	}
	

}
