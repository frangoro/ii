package org.frangoro.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.frangoro.dao.ItemDao;
import org.frangoro.domain.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ItemDaoHbn implements ItemDao{

	Logger log = LoggerFactory.getLogger(ItemDaoHbn.class);
	
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

	@Override
	@Transactional
	public Boolean create(Items item) {
		log.debug("persist item code: " + item.getCode());
		try {
			em.persist(item);
			log.debug("persist successful");
			return true;
		} catch (RuntimeException re) {
			log.error("persist failed for item code: " + item.getCode(), re);
			throw re;
		}
	}
	
	public Items findByCode(String code) {
		log.debug("Get the item code: " + code);
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Items> cq = cb.createQuery(Items.class);
			Root<Items> from = cq.from(Items.class);
			cq.select(from);
			cq.where(cb.equal(from.get("code"), code));
			List<Items> items = em.createQuery(cq).getResultList();
			if (items != null && !items.isEmpty()) {
				log.debug("get successful");
				return items.get(0);
			}
			log.warn("Not found item with code: " + code);
			return null;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@Override
	public Items findAvailableById(Long id) {
		log.debug("Get the item id: " + id);
		try {
			Query query = em.createQuery("from Items i where i.id = :id "
					+ "and i.id in (select t.items.id from Transactions t "
					+ "where (t.operation like 'NEW' or t.operation like 'RETURNED') AND transaction_date "
					+ "in( select max(TRANSACTION_DATE) from Transactions group by items.id))");
			query.setParameter("id", id);
			Items item = (Items)query.getSingleResult();
			if (item == null) {
				log.warn("Not found item with id: " + id);
			}
			return item;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
