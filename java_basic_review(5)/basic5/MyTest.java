package com.review.java.basic5;

import java.util.ArrayList;

import org.junit.Test;

import com.review.java.model.Employee;
import com.review.java.model.Manager;
import com.review.java.model.Person;
import com.review.java.model.Size;
import com.review.java.model.Student;

public class MyTest {
	
	@Test
	public void testArrayConvert() {
		Manager[] mArray = new Manager[3];;
		Employee[] eArray = new Employee[3]; 
		Manager mTemp;
		Employee eTemp = new Employee();
		
		if(eTemp instanceof Manager) {
			mTemp = (Manager)eTemp; 
		}
		
		eArray = mArray; // 编译器允许数组对象转换.
		eArray[0] = new Employee(1, "zhangfei", 13000); // throws exception.
		mArray[0].setBonus(1000);
		System.out.println(mArray[0].getSalary());
	}
	
	@Test
	public void testGetClass() {
		Employee m = new Manager(1, "1", 1000);
		
		System.out.println(m.getClass());
	}
	
	@Test
	public void testEqualsOverride() {
		Manager m1 = new Manager(1, "1", 1);
		Employee m2 = new Employee(1, "1", 1);
		
		System.out.println(m1.equals(m2));
	}
	
	@Test
	public void testInstanceof() {
		Person p = new Person();
		Student s = new Student();
		
		System.out.println(p.equals(s)); // true.
		System.out.println(s.equals(p)); // true.
	}
	
	@Test
	public void testHashCode() {
		Manager m1 = new Manager(1, "1", 1);
		Employee m2 = new Employee(1, "1", 1);
		
		System.out.println(m1.equals(m2));
		System.out.println(m1.hashCode() == m2.hashCode());
	}
	
	@Test
	public void testArrayList() {
		ArrayList<Double> list = new ArrayList<>();
		list.ensureCapacity(3);
		list.add(Double.valueOf(1));
		list.add(Double.valueOf(2));
		list.add(Double.valueOf(3));
		list.trimToSize();// 不会报错，只是告诉jvm 回收多余的内存.
		list.add(Double.valueOf(4));
		System.out.println(list.size());
		
		Double[] dArray = new Double[list.size()];  
		list.toArray(dArray); // 访问数组元素
		for(double d : dArray) {
			System.out.print(d + ", ");
		}
		
		int n = list.size() / 2;
		list.add(n, Double.valueOf(5)); // 在 数组列表中间插入元素.这种操作如果多的话，建议 LinkedList		
		System.out.println("\n" + "new array: ");
		for (Double temp : list) {
			System.out.println(temp);
		}
		
	}
	
	@Test // 参数数量可变的方法 ( Object... == Object[] )
	public void testVariableParameters() {
		System.out.printf("%d %s", new Object[]{new Integer(100), "widgets"});
		prettyPrint(100, "widgets");
		prettyPrint(new Object[]{new Integer(100), "widgets"});
	}
	public void prettyPrint(Object... args) {
		System.out.printf("\n%d %s", args);
	}
	
	@Test
	public void testEnum() {
		Size[] values = Size.values();
		for(Size size : values) {
			System.out.println(size);
		}
		
		int ordinal = Size.MEDIUM.ordinal();
		System.out.println(ordinal);
		
		// 将s 设置为 Size.SMALL；
		Size s = Enum.valueOf(Size.class, "SMALL");
		System.out.println(s);
	}
	
	
}
