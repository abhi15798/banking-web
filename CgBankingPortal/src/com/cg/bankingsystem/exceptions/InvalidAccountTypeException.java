package com.cg.bankingsystem.exceptions;

public class InvalidAccountTypeException extends RuntimeException{
	String s;
	public InvalidAccountTypeException(String s) {
		this.s=s;}
	@Override
	public String toString() {
		return "InvalidAccountTypeException [s=" + s + "]";
	}
	 
}
