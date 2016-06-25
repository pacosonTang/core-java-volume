package com.review.java.model;

import java.util.Objects;

public class Employee {
	private int age;
	private String name;
	private double salary;
	
	public Employee(){}
	
	public Employee(int age, String name, double salary) {
		this.age = age;
		this.name = name;
		this.salary = salary;
	}
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	/*@Override
	public int hashCode() {
		return 7*name.hashCode()
				+ 11*String.valueOf(salary).hashCode()
				+ 13*String.valueOf(age).hashCode();
	}*/
	
	@Override // defined in Employee via jdk7.
	public int hashCode() {
		return Objects.hash(age, name, salary);
	}
	
	public void prettyPrint(Object... objs) {
		
	}
	
}
