package com.cg.bankingsystem.exceptions;

public class InvalidPinNumberException extends RuntimeException{
	String s;
	public InvalidPinNumberException(String s) {
		// TODO Auto-generated constructor stub
		this.s=s;
	}
	@Override
	public String toString() {
		return "InvalidPinNumberException [s=" + s + "]";
	}
	
}
