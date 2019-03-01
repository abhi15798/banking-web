package com.cg.bankingsystem.daoservices;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.cg.bankingsystem.beans.Transaction;
public class TransactionDaoImpl implements TransactionDAO{
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Transaction save(Transaction transaction, long accountNo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return transaction;
	}

	@Override
	public boolean update(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction findOne(int transactionId) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Transaction transaction=entityManager.find(Transaction.class, transactionId);
		return transaction;
	}

	@Override
	public List<Transaction> findAll(long accountNo) {

		return entityManagerFactory.createEntityManager().createQuery("from Transaction t where ACCOUNT_ACCOUNTNO="+accountNo,Transaction.class).getResultList();
	}

}