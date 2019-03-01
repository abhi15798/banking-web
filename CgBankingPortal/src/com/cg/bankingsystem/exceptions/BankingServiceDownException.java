package com.cg.bankingsystem.exceptions;

public class BankingServiceDownException extends RuntimeException{
	String s;
	public BankingServiceDownException(String s) {
		this.s=s;}
	@Override
	public String toString() {
		return "BankingServiceDownException [s=" + s + "]";
	}
	
}
