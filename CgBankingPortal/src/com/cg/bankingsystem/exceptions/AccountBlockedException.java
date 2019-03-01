package com.cg.bankingsystem.exceptions;

public class AccountBlockedException extends RuntimeException {
	String s;
	public AccountBlockedException(String s) {
		this.s=s;}
	@Override
	public String toString() {
		return "AccountBlockedException [s=" + s + "]";
	}
	
}
