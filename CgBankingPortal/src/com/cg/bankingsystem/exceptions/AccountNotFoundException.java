package com.cg.bankingsystem.exceptions;

public class AccountNotFoundException extends RuntimeException {
    String s;
	public AccountNotFoundException(String s) {
		// TODO Auto-generated constructor stub
    	this.s=s;
	}
	@Override
	public String toString() {
		return "AccountNotFoundException [s=" + s + "]";
	}
	
}
