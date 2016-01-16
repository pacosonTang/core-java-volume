package com.corejava.chapter1;

import java.io.*;
import static java.lang.System.*;

public class ObjectStreamTest
{
	public static void main(String[] args) throws Exception
	{
		Orientation original = Orientation.HORIZONTAL;
		Orientation saved;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.dat")))
		{
			oos.writeObject(original);// writing over.
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.dat"));)
		{
			saved = (Orientation) ois.readObject();
		}  
		if(saved == Orientation.HORIZONTAL)
		{
			out.println("bingo");
		}
		else
		{
			out.println("Error");
		}
	}
   public static void main1(String[] args) throws IOException, ClassNotFoundException
   {
      Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
      Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
      carl.setSecretary(harry);
      Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
      tony.setSecretary(harry);

      Employee[] staff = new Employee[3];
      staff[0] = carl;
      staff[1] = harry;
      staff[2] = tony;
      
      // save all employee records to the file employee.dat         
      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))) 
      {
         out.writeObject(staff);
      }
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat")))
      {
         // retrieve all records into a new array
         Employee[] newStaff = (Employee[]) in.readObject();
//          raise secretary's salary
         newStaff[1].raiseSalary(10);
         // print the newly read employee records
         for (Employee e : newStaff)
            System.out.println(e);
      }
   }
}
class Orientation implements  Serializable
{
	private int value;
	public static final Orientation HORIZONTAL = new Orientation(1);
	public static final Orientation VERTICAL = new Orientation(2);
	private Orientation(int value)
	{
		this.value = value;
	}
	protected Object readResolve() throws ObjectStreamException
	{
		if(value == 1){
			return Orientation.HORIZONTAL;
		}else if(value == 2){
			return Orientation.VERTICAL;
		}else{
			return null;
		}
	}
}