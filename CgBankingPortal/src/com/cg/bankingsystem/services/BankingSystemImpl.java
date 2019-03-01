package com.cg.bankingsystem.services;
import java.util.List;
import java.util.Random;
import com.cg.bankingsystem.beans.Account;
import com.cg.bankingsystem.beans.Transaction;
import com.cg.bankingsystem.daoservices.AccountDAO;
import com.cg.bankingsystem.daoservices.AccountDaoImpl;
import com.cg.bankingsystem.daoservices.TransactionDAO;
import com.cg.bankingsystem.daoservices.TransactionDaoImpl;
import com.cg.bankingsystem.exceptions.AccountBlockedException;
import com.cg.bankingsystem.exceptions.AccountNotFoundException;
import com.cg.bankingsystem.exceptions.BankingServiceDownException;
import com.cg.bankingsystem.exceptions.InsufficientAmountException;
import com.cg.bankingsystem.exceptions.InvalidAccountTypeException;
import com.cg.bankingsystem.exceptions.InvalidAmountException;
import com.cg.bankingsystem.exceptions.InvalidPinNumberException;

public class BankingSystemImpl implements BankingServices {
 AccountDAO accountdao=new AccountDaoImpl();
 TransactionDAO transactiondao=new TransactionDaoImpl();
 Transaction transaction=new Transaction();
 int count=3;
	@Override
	public Account openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServiceDownException {
			Account account = new Account(getPIN_NUMBER(), accountType, "Active", initBalance);
			account=accountdao.save(account);
		    return account;      
	}
	
	public void checkPin(long accounNo,int pinNumber) throws AccountBlockedException ,InvalidPinNumberException
	{
		Account account=accountdao.findOne(accounNo);
		if(account.getPinNumber()!=pinNumber)
			{
			    count--;
			    if(count==0)
			    {
			    	account.setAccountStatus("Blocked");
			    	accountdao.update(account);
			    	throw new AccountBlockedException("Account BLocked="+accounNo);
			    }
				throw new InvalidPinNumberException("Pin is incorrect");		 	
			}
	}
	@Override
	public float depositAmount(long accountNo, float amount,int pinNumber)
			throws AccountNotFoundException, BankingServiceDownException, AccountBlockedException {
		Account account= getAccountDetails(accountNo);
		Transaction transaction=new Transaction(amount, "Deposit",account);
		account.setAccountBalance(account.getAccountBalance()+amount);
		
		transaction=transactiondao.save(transaction,accountNo);
		accountdao.update(account);	
		return account.getAccountBalance();
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServiceDownException, AccountBlockedException {
		// TODO Auto-generated method stub
		Account account=getAccountDetails(accountNo);
		if(account.getPinNumber()!=pinNumber)
			throw new InvalidPinNumberException( "Incorrect pin");
		if(amount>account.getAccountBalance())
			throw new InsufficientAmountException("Not Enough Balance");
		account.setAccountBalance(account.getAccountBalance()-amount);
		Transaction transaction=new Transaction(amount, "WithDraw",account);
		transaction=transactiondao.save(transaction, accountNo);
		accountdao.update(account);
		return account.getAccountBalance();
	}

	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException {
		 Account account=getAccountDetails(accountNoTo);
		 int pinNumberTo=account.getPinNumber();
		  withdrawAmount(accountNoFrom, transferAmount, pinNumber);
		  depositAmount(accountNoTo, transferAmount, pinNumberTo);
		 return true;
	}

	@Override
	public Account getAccountDetails(long accountNo) throws BankingServiceDownException, AccountNotFoundException {
		// TODO Auto-generated method stub
		Account account=accountdao.findOne(accountNo);
		if(account==null)
			throw new AccountNotFoundException("Account not present="+accountNo);
		return account;
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServiceDownException {
		// TODO Auto-generated method stub
		return accountdao.findAll();
	}

	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo,int pinNumber)
			throws BankingServiceDownException, AccountNotFoundException {
		Account account=getAccountDetails(accountNo);
		if(account==null)
				throw new AccountNotFoundException("Account not present= "+accountNo);
		if(pinNumber!=account.getPinNumber())
			throw new InvalidPinNumberException("Pin is not valid");
		//ArrayList<Transaction>transactionsList=new ArrayList<>(account.transactions.values());
		return transactiondao.findAll(accountNo);
	}

	@Override
	public String updateAccountStatus(long accountNo)
			throws BankingServiceDownException, AccountNotFoundException, AccountBlockedException {
		// TODO Auto-generated method stub
		Account account=getAccountDetails(accountNo);
		if(account.getAccountStatus()=="Blocked")
			account.setAccountStatus("Active");
		else if(account.getAccountStatus()=="Active");
			account.setAccountStatus("Blocked");
		accountdao.update(account);	
		return account.getAccountStatus();
	}

	@Override
	public Account verifyAccountDetails(long accountNo) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account account=getAccountDetails(accountNo);
		if(account==null)
			throw new AccountNotFoundException("Account not present="+accountNo);
		else if(account.getAccountStatus()=="Blocked")
			throw new AccountBlockedException("Account is Blocked");
		return account;
	}

	@Override
	public int generateNewPin(long accountNo) throws AccountNotFoundException {
		Account account=getAccountDetails(accountNo);
		if(account==null)
			throw new AccountNotFoundException("Account Not present="+accountNo);
		int newPin=getPIN_NUMBER();
		account.setPinNumber(newPin);
		account.setAccountStatus("Active");
		accountdao.update(account);
		return newPin;
	}
	public  int getPIN_NUMBER() {
		return (int)Math.pow(5, 5-1)+new Random().nextInt(9000);
    }
}
