package org.frangoro.domain;
// Generated 06-jul-2016 14:46:02 by Hibernate Tools 5.0.0.Alpha3

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Locations.
 * @see org.frangoro.domain.Locations
 * @author Hibernate Tools
 */
@Stateless
public class LocationsHome {

	private static final Log log = LogFactory.getLog(LocationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Locations transientInstance) {
		log.debug("persisting Locations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Locations persistentInstance) {
		log.debug("removing Locations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Locations merge(Locations detachedInstance) {
		log.debug("merging Locations instance");
		try {
			Locations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Locations findById(long id) {
		log.debug("getting Locations instance with id: " + id);
		try {
			Locations instance = entityManager.find(Locations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
