package org.frangoro.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.frangoro.dao.ItemTranLocDao;
import org.frangoro.domain.ItemsTransLoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Operaciones de consulta sobre la vista que representa el ente: Itemas -
 * Transacciones - Localizaciones
 */
@Repository
public class ItemTranLocDaoHbn implements ItemTranLocDao {

	Logger log = LoggerFactory.getLogger(ItemTranLocDaoHbn.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Encuentra la información de un item con el estado correspondiente 
	 * a su última operación y la localización actual.
	 * @param id
	 * @return
	 */
	@Override
	public ItemsTransLoc findById(Long id) {
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

	/**
	 * Encuentra los datos correspondientes a la última transacción de
	 * todos los items.
	 */
	@Override
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
	
	/**
	 * Encuentra los datos correspondientes a la última transacción de
	 * todos los items aplicando el filtro de búsqueda.
	 */
	//TODO: Modularizarlo para que sea más mantenible y reusable
	@Override
	public List<ItemsTransLoc> queryFilter(Map<String, String> filter) {
		log.debug("getting all ItemsTransLoc instances with filters");
		try {
			final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery();
			
			final Root from = criteria.from(ItemsTransLoc.class);
			criteria.select(from);

			Predicate where = builder.disjunction();
			for (Entry<String, String> attr : filter.entrySet())
			{
				where = builder.and(where, builder.equal(from.get(attr.getKey()), attr.getValue()));
			}
			criteria.where(where);
			
			List<ItemsTransLoc> results = entityManager.createQuery(criteria).getResultList();
			
			log.debug("get successful");
			return results;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
