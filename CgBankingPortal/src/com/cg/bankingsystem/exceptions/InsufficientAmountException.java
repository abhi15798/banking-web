package com.cg.bankingsystem.exceptions;

public class InsufficientAmountException extends RuntimeException{
 String s;
 	public InsufficientAmountException(String s) {
	this.s=s;
	}
	@Override
	public String toString() {
		return "InsufficientAmountException [s=" + s + "]";
	}
 	
}
