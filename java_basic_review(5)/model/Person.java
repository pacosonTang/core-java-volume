package com.review.java.model;

 public class Person {
	private String name;
	
	public Person(){}
	
	public Person(String n) {
		name = n;
	}

	public String getDescription(){
		return null;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			return true;
		}
		return false;
	}
}
