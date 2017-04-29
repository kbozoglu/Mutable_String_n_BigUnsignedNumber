package Jaq.hw5;

import sun.font.CStrike;

/**
 * File Name: Cstring.java 
 * Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: CharArray.java Cstring
 */

class Cstring {
  private CharArray d; //Infinte array of char
  static IntUtil u = new IntUtil();
  
  //WRITE ALL THE REQUIRED CODE BELOW /////////////////////////////////////////////////
  private int length;
  public int getLength(){
    return length;
  }

  public char getChar(int i){
    return d.get(i);
  }
  public void setChar(int i, char ch){
    d.set(i, ch);
    if(i == length){
      length++;
      endCstring(length);
    }
  }

  // constructor that takes nothing
  public Cstring(){
    d = new CharArray(1);
    endCstring(0);
  }

  // constructor that takes character
  public Cstring(char ch){
    d = new CharArray(2);
    d.set(0, ch);
    endCstring(1);
  }

  // constructor that takes string
  public Cstring(String str){
    d = new CharArray(str.length() + 1);
    for(int i = 0; i < str.length(); i++){
      d.set(i, str.charAt(i));
    }
    endCstring(str.length());
  }

  public void endCstring(int len){
    length = len;
    d.set(length, '\0');
  }

  //clone
  public Cstring clone(){
    Cstring res = new Cstring();
    for(int i = 0; i < length; i++){
      res.d.set(i, d.get(i));
    }
    res.endCstring(length);
    return res;
  }

  // reverse
  public void reverse(){
    int left = 0, right = length - 1;
    while(left < right){
      char temp = d.get(left);
      d.set(left, d.get(right));
      d.set(right, temp);
      left++;
      right--;
    }
  }

  // append that takes string
  public void append(String str){
    for(int i = length, j = 0; j < str.length(); i++, j++){
      d.set(i, str.charAt(j));
    }
    endCstring(length + str.length());
  }

  // append that takes Cstring
  public void append(Cstring cs){
    for(int i = length, j = 0; j < cs.length; i++, j++){
      d.set(i, cs.d.get(j));
    }
    endCstring(length + cs.length);
  }

  // add that takes string
  public Cstring add(String str){
    Cstring res = clone();
    res.append(str);
    return res;
  }

  // add that takes Cstring
  public Cstring add(Cstring cs){
    Cstring res = clone();
    res.append(cs);
    return res;
  }

  //isEqual
  public boolean isEqual(Cstring cs) {
    if (length != cs.length) return false;
    for (int i = 0; i < length; i++) {
      if (d.get(i) != cs.d.get(i)) return false;
    }
    return true;
  }

  public void pLn(String str){
    System.out.print(str);
    for(int i = 0; i < length; i++){
      System.out.print(d.get(i));
    }
    System.out.println();
  }

  //NOTHING CAN BE CHANGED BELOW. EVERYTHING MUST WORK AS IS ////////////////////////////////////
  private static void testBasic() {
    Cstring a = new Cstring('b') ;
    a.pLn("a = ") ;
    Cstring b = new Cstring('7') ;
    b.pLn("b = ") ;
    Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    c.pLn("c = ") ;
    Cstring d = c.clone() ;
    d.pLn("d = ") ;
    Cstring e = new Cstring("A quick brown fox junped over a lazy dog") ;
    e.pLn("e = ") ;
    Cstring f = new Cstring("Gateman sees name garageman sees nametag") ;
    f.pLn("f =  ") ;
    f.reverse() ;
    f.pLn("f' = ") ;
  }

  private static void testAdd() {
    Cstring a = new Cstring("UCSC") ;
    Cstring b = new Cstring("Extension") ;
    Cstring c = a.add(b) ;
    a.pLn("a = ") ;
    b.pLn("b = ") ;
    c.pLn("c = ") ;
    Cstring d = c.add("USA") ;
    d.pLn("d = ") ;
    a.append(b) ;
    a.pLn("a+b = ") ;
    a.append("World") ;
    a.pLn("a+b+World = ") ;
  }

  private static void testEqual() {
    Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    a.pLn("a = ") ;
    Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    b.pLn("b = ") ;
    u.myassert(a.isEqual(b)) ;
    Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789") ;
    c.pLn("c = ") ;
    u.myassert(a.isEqual(c) == false) ;
  }

  private static void testBench() {
    System.out.println("-----------Basic----------------");
    testBasic() ;
    System.out.println("-----------Addition----------------");
    testAdd() ;
    System.out.println("-----------Equal----------------");
    testEqual() ;
  }

  public static void main(String[] args) {
    System.out.println("Cstring.java");
    testBench();
    System.out.println("Done");
  }

}