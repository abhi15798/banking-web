package com.cg.bankingsystem.daoservices;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.bankingsystem.beans.Account;
public class AccountDaoImpl implements AccountDAO{
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Account save(Account account) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

	@Override
	public boolean update(Account account) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return false;
	}

	@Override
	public Account findOne(long accountNo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Account account=entityManager.find(Account.class, accountNo);
		return account;

	}

	@Override
	public List<Account> findAll() {
		return entityManagerFactory.createEntityManager().createQuery("from Account a").getResultList();
	}
	
}
