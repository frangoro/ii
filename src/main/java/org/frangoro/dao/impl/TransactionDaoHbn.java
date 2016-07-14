package org.frangoro.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frangoro.dao.ItemDao;
import org.frangoro.dao.TransactionDao;
import org.frangoro.domain.Items;
import org.frangoro.domain.Transactions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionDaoHbn implements TransactionDao{

	private static final Log log = LogFactory.getLog(TransactionDaoHbn.class);
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Items> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Items item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Items findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Items item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Items delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transactions findByItemId(Long id) {
		log.debug("Get the Transaction id: " + id);
		try {
			Transactions instance = em.find(Transactions.class,id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public Boolean create(Transactions transactionNew) {
		log.debug("persist Transaction id: " + transactionNew.getId());
		try {
			em.persist(transactionNew);
			log.debug("persist successful");
			return true;
		} catch (RuntimeException re) {
			log.error("persist failed for item code: " + transactionNew.getId(), re);
			throw re;
		}
	}
	
}
