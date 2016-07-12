package org.frangoro.domain;
// Generated 06-jul-2016 14:46:02 by Hibernate Tools 5.0.0.Alpha3

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ItemsTransLoc.
 * @see org.frangoro.domain.ItemsTransLoc
 * @author Hibernate Tools
 */
@Stateless
public class ItemsTransLocHome {

	private static final Log log = LogFactory.getLog(ItemsTransLocHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemsTransLoc transientInstance) {
		log.debug("persisting ItemsTransLoc instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemsTransLoc persistentInstance) {
		log.debug("removing ItemsTransLoc instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemsTransLoc merge(ItemsTransLoc detachedInstance) {
		log.debug("merging ItemsTransLoc instance");
		try {
			ItemsTransLoc result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

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
}
