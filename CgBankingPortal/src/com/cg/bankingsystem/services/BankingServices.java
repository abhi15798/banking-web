package com.cg.bankingsystem.services;

import java.util.List;
import com.cg.bankingsystem.beans.Account;
import com.cg.bankingsystem.beans.Transaction;
import com.cg.bankingsystem.exceptions.AccountBlockedException;
import com.cg.bankingsystem.exceptions.AccountNotFoundException;
import com.cg.bankingsystem.exceptions.BankingServiceDownException;
import com.cg.bankingsystem.exceptions.InsufficientAmountException;
import com.cg.bankingsystem.exceptions.InvalidAccountTypeException;
import com.cg.bankingsystem.exceptions.InvalidAmountException;
import com.cg.bankingsystem.exceptions.InvalidPinNumberException;

public interface BankingServices {
	Account openAccount(String accountType,float initBalance) throws InvalidAmountException,InvalidAccountTypeException,
	BankingServiceDownException;
	
	float depositAmount(long accountNo,float amount,int pinNumber)
		throws AccountNotFoundException,BankingServiceDownException,AccountBlockedException;
	
	float withdrawAmount(long accountNo,float amount,int pinNumber)
			throws InsufficientAmountException,AccountNotFoundException,InvalidPinNumberException,
			BankingServiceDownException,AccountBlockedException;
	
	boolean fundTransfer(long accountNoTo,long accountNoFrom,float transferAmount,int pinNumber)
		throws InsufficientAmountException,AccountNotFoundException,InvalidPinNumberException;
	
	Account getAccountDetails(long accountNo)
		throws BankingServiceDownException,AccountNotFoundException;
	 int generateNewPin(long accountNo)throws AccountNotFoundException;
	Account verifyAccountDetails(long accountNo)
		throws AccountNotFoundException;
	List<Account> getAllAccountDetails()
		throws BankingServiceDownException;
	
	List <Transaction> getAccountAllTransaction(long accountNo,int pinNumber)
		throws BankingServiceDownException,AccountNotFoundException;
	
	public String updateAccountStatus(long accountNo)
		throws BankingServiceDownException,AccountNotFoundException,AccountBlockedException;

	public void checkPin(long accountNo, int pinNumber);	
	  public int getPIN_NUMBER();
}
