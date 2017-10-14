package com.example.web;

public class Sgsn {
	private int cost;
	private int capacity;
	private int connections;
	
	
	public Sgsn (int x, int y) {
		
		this.cost=x;
		this.capacity=y;
	}
	
	public int get_capacity() {
		return this.capacity;
	}
	
	public void increase_capacity(int ammount) {
		this.capacity=this.capacity+ammount;
	}
	
	public void decrease_capacity(int ammount) {
		this.capacity=this.capacity-ammount;
	}
	
	public void set_connections(int con) {
		this.connections=con;
	}
	public int get_connections() {
		return this.connections;
	}
}
