package com.cg.bankingsystem.exceptions;

public class InvalidAmountException extends RuntimeException{
 String s;
 public InvalidAmountException(String s) {
	 this.s=s;}
@Override
public String toString() {
	return "InvalidAmountException [s=" + s + "]";
}
 
}
