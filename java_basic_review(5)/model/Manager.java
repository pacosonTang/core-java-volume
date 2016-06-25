package com.review.java.model;

import java.util.Objects;

public class Manager extends Employee{
	private double bonus;
	
	public Manager(int age, String name, double salary) {
		super(age, name, salary);
	}

	@Override
	public double getSalary() {		
		return super.getSalary() + bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	/*@Override 
	public boolean equals(Employee obj){}*/
	
	@Override // defined in Manager
	public boolean equals(Object obj) {
		if(this == obj) { // step1: 检测this 与 obj 是否引用同一个对象
			return true;
		} else if(obj == null) { // step2: 检测 obj 是否为 null
			return false;
		} else if(getClass() != obj.getClass()) { // step3: 比较this 与 obj 是否属于同一个类
			return false;
		} else { // step4: 将 obj 转换为 相应的类类型变量
			Manager m = (Manager)obj;
			return getName()==m.getName() && getAge()==m.getAge() && getSalary()==m.getSalary()
					&& bonus == m.getBonus();
		}
	}

	public double getBonus() {
		return bonus;
	}
	
	@Override
	public int hashCode() { // defined in Manager.(Manager extends Employee.)
		return super.hashCode() + Objects.hash(bonus);
	}
	
}
