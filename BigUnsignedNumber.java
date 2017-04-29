package Jaq.hw5;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * File Name: BigUnsignedNumber.java 
 * Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2017
 */
/*
 * To compile you require: BigUnsignedNumber.java CharArray.java Cstring IntUtil.java
 */

class BigUnsignedNumber {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  
  public void pLn(String t) {
    d.pLn(t) ;
  }
  
  //WRITE ALL THE REQUIRED PROCEDURE REQUIRED TO COMPILE AND RUN BigUnsignedNumberTester.java 
  //NOTHING CAN BE CHANGED IN BigUnsignedNumberTester.java

  //constructor that takes character
  public BigUnsignedNumber(char ch){
    d = new Cstring(ch);
  }

  //constructor that takes integer
  public BigUnsignedNumber(int n){
    d = new Cstring((char)(n%10 + '0'));
    n/=10;
    while(n > 0){
      Cstring cs = new Cstring((char)(n%10 + '0'));
      d.append(cs);
      n /= 10;
    }
    d.reverse();
  }

  //constructor that takes string
  public BigUnsignedNumber(String s){
    d = new Cstring(s);
  }

  //clone
  public BigUnsignedNumber clone(){
    BigUnsignedNumber res = new BigUnsignedNumber('0');
    res.d = d.clone();
    return res;
  }

  //isEqual that takes BigUnsignedNumber
  public boolean isEqual(BigUnsignedNumber bun){
    return d.isEqual(bun.d);
  }

  //isEqual that takes String
  public boolean isEqual(String s){
    Cstring cs = new Cstring(s);
    return d.isEqual(cs);
  }

  public boolean isEqual(int n){
    Cstring num = new Cstring((char)(n%10 + '0'));
    n/=10;
    while(n > 0){
      Cstring cs = new Cstring((char)(n%10 + '0'));
      num.append(cs);
      n /= 10;
    }
    num.reverse();
    return d.isEqual(num);
  }

  public BigUnsignedNumber add(BigUnsignedNumber bun){
    int i = d.getLength() - 1;
    int j = bun.d.getLength() - 1;
    int carry = 0;
    int idx = 0;
    BigUnsignedNumber sum = new BigUnsignedNumber('0');

    while(i >= 0 || j >= 0){
      int digit1 = 0, digit2 = 0;

      if(i >= 0) digit1 = d.getChar(i) - '0';
      if(j >= 0) digit2 = bun.d.getChar(j) - '0';

      char sumOfDigits = (char)(((digit1 + digit2 + carry) % 10) + '0') ;
      sum.d.setChar(idx, sumOfDigits);

      carry = (digit1 + digit2 + carry) > 9 ? 1 : 0;
      i--;
      j--;
      idx++;
    }
    sum.d.endCstring(idx);
    if(carry == 1) sum.d.append("1");
    sum.d.reverse();
    return sum;
  }

  //TODO: special mult case for 0
  public BigUnsignedNumber mult(BigUnsignedNumber bun){ //TODO: special case for 0
    BigUnsignedNumber res = new BigUnsignedNumber('0');
    for(int i = 1; i < d.getLength() + bun.d.getLength(); i++){
      res.d.append("0");
    }
    int shift = 0, idx = 0;
    for(int i = d.getLength() - 1; i >= 0; i--){
      idx = shift;
      int carry = 0;
      char ch;
      for(int j = bun.d.getLength() - 1; j >= 0; j--){
        int mult = (res.d.getChar(idx) - '0') + (d.getChar(i) - '0') * (bun.d.getChar(j) - '0') + carry;
        int digit = mult % 10;
        carry = mult / 10;
        ch = (char)(digit + '0');
        res.d.setChar(idx, ch);
        idx++;
      }
      shift++;
      ch = (char)(carry + '0');
      res.d.setChar(idx, ch);
    }
    if(res.d.getChar(idx) == '0'){
      res.d.endCstring(idx);
    }
    else{
      res.d.endCstring(idx + 1);
    }
    res.d.reverse();
    return res;
  }

  public static BigUnsignedNumber factorial(int n){
    BigUnsignedNumber num = new BigUnsignedNumber(n);
    BigUnsignedNumber i = new BigUnsignedNumber(1);
    BigUnsignedNumber one = new BigUnsignedNumber(1);
    BigUnsignedNumber fact = new BigUnsignedNumber(1);
    while(!(i.isEqual(num))){
      i = i.add(one);
      fact = fact.mult(i);
    }
    return fact;
  }
  public int size(){
    return d.getLength();
  }

}
