package com.cg.bankingsystem.daoservices;
import java.util.List;
import com.cg.bankingsystem.beans.Account;
public interface AccountDAO {
	Account save(Account account);
	boolean update(Account account);
	Account findOne(long accountNo);
	List <Account> findAll();
}
