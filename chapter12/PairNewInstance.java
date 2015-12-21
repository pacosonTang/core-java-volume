package com.corejava.chapter12;

public class PairNewInstance<T> 
{
   private T first;
   private T second;

   public PairNewInstance()
   {
	   first = T.class.newInstance();
   }
   
   public static<T> PairNewInstance<T> makePair(Class<T> c1)
   {
       try
       { 
    	   return new PairNewInstance<>(c1.newInstance(), c1.newInstance()); 
       }
       catch(Exception ex) 
       {
    	   return null;
       }
   }
   
  /* public Pair() 
   {
	   first = new T();
	   second = new T();
   }*/
   public PairNewInstance(T first, T second) 
   { 
	   this.first = first;  
	   this.second = second; 
   }

   public T getFirst() { return first; }
   public T getSecond() { return second; }

   public void setFirst(T newValue) { first = newValue; }
   public void setSecond(T newValue) { second = newValue; }
}
