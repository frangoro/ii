package org.frangoro.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frangoro.dao.ItemTranLocDao;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.domain.ItemsTransLocId;
import org.springframework.stereotype.Repository;

@Repository
public class ItemTranLocDaoHbn implements ItemTranLocDao {

	private static final Log log = LogFactory.getLog(ItemTranLocDaoHbn.class);

	@PersistenceContext
	private EntityManager entityManager;

	public ItemsTransLoc findById(ItemsTransLocId id) {
		log.debug("getting ItemsTransLoc instance with id: " + id);
		try {
			ItemsTransLoc instance = entityManager.find(ItemsTransLoc.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ItemsTransLoc> findAll() {
		log.debug("getting all ItemsTransLoc instances");
		try {
			List<ItemsTransLoc> instances = entityManager
					.createQuery("select itl from ItemsTransLoc itl", ItemsTransLoc.class).getResultList();
			log.debug("get successful");
			return instances;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
