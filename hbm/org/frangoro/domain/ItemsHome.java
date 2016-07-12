package org.frangoro.domain;
// Generated 06-jul-2016 14:46:02 by Hibernate Tools 5.0.0.Alpha3

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Items.
 * @see org.frangoro.domain.Items
 * @author Hibernate Tools
 */
@Stateless
public class ItemsHome {

	private static final Log log = LogFactory.getLog(ItemsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Items transientInstance) {
		log.debug("persisting Items instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Items persistentInstance) {
		log.debug("removing Items instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Items merge(Items detachedInstance) {
		log.debug("merging Items instance");
		try {
			Items result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Items findById(long id) {
		log.debug("getting Items instance with id: " + id);
		try {
			Items instance = entityManager.find(Items.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
