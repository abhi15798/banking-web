package com.cg.bankingsystem.daoservices;
import java.util.List;
import com.cg.bankingsystem.beans.Transaction;
public interface TransactionDAO {
	Transaction save(Transaction transaction,long accountNo);
	boolean update(Transaction transaction);
	Transaction findOne(int transactionId);
	List<Transaction> findAll(long accountNo);
}
