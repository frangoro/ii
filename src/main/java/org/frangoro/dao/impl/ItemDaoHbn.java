package org.frangoro.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frangoro.dao.ItemDao;
import org.frangoro.domain.Items;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ItemDaoHbn implements ItemDao{

	private static final Log log = LogFactory.getLog(ItemDaoHbn.class);
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Items> findAll() {
		log.debug("Get all Items intances.");
		try {
			List<Items> instances = em.createQuery("select i from Items i", Items.class).getResultList();
			log.debug("get successful");
			return instances;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional
	public Boolean update(Items item) {
		log.debug("Update the item.");
		try {
			return em.merge(item) != null;
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public Items findById(Long id) {
		log.debug("Get the item id: " + id);
		try {
			Items instance = em.find(Items.class,id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
