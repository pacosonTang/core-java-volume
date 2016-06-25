package com.review.java.model;

public class Student extends Person {
	private String major;

	public Student(){}
	
	public Student(String n, String m) {
		super(n);
		major = m;
	}

	public String getDescription() {
		return "a student majoring in " + major;
	}
}