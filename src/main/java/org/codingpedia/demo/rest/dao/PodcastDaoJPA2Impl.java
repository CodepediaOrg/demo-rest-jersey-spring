package org.codingpedia.demo.rest.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class PodcastDaoJPA2Impl implements PodcastDao {

	@PersistenceContext(unitName="demoRestPersistence")
	private EntityManager entityManager;

	@PersistenceContext(unitName="demoRestPersistenceLegacy")
	private EntityManager entityManagerLegacy;
	
	public List<PodcastEntity> getPodcasts(String orderByInsertionDate) {
		String sqlString = null;
		if(orderByInsertionDate != null){
			sqlString = "SELECT p FROM PodcastEntity p" + " ORDER BY p.insertionDate " + orderByInsertionDate;
		} else {
			sqlString = "SELECT p FROM PodcastEntity p";
		}		 
		TypedQuery<PodcastEntity> query = entityManager.createQuery(sqlString, PodcastEntity.class);		

		return query.getResultList();
	}

	public List<PodcastEntity> getRecentPodcasts(int numberOfDaysToLookBack) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC+1"));//Munich time 
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -numberOfDaysToLookBack);//substract the number of days to look back 
		Date dateToLookBackAfter = calendar.getTime();
		
		String qlString = "SELECT p FROM PodcastEntity p where p.insertionDate > :dateToLookBackAfter ORDER BY p.insertionDate DESC";
		TypedQuery<PodcastEntity> query = entityManager.createQuery(qlString, PodcastEntity.class);		
		query.setParameter("dateToLookBackAfter", dateToLookBackAfter, TemporalType.DATE);

		return query.getResultList();
	}
	
	public PodcastEntity getPodcastById(Long id) {
		
		try {
			String qlString = "SELECT p FROM PodcastEntity p WHERE p.id = ?1";
			TypedQuery<PodcastEntity> query = entityManager.createQuery(qlString, PodcastEntity.class);		
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public PodcastEntity getPodcastByFeed(String feed) {
		
		try {
			String qlString = "SELECT p FROM PodcastEntity p WHERE p.feed = ?1";
			TypedQuery<PodcastEntity> query = entityManager.createQuery(qlString, PodcastEntity.class);		
			query.setParameter(1, feed);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	

	public void deletePodcastById(Long id) {
		
		PodcastEntity podcast = entityManager.find(PodcastEntity.class, id);
		entityManager.remove(podcast);
		
	}

	public Long createPodcast(PodcastEntity podcast) {
		
		podcast.setInsertionDate(new Date());
		entityManager.merge(podcast);
		entityManager.flush();//force insert to receive the id of the podcast
		
		return podcast.getId();
	}

	public void updatePodcast(PodcastEntity podcast) {
		//TODO think about partial update and full update 
		entityManager.merge(podcast);		
	}
	
	public void deletePodcasts() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE podcasts");		
		query.executeUpdate();
	}

	public List<PodcastEntity> getLegacyPodcasts() {
		
		String qlString = "SELECT p FROM PodcastEntity p";
		TypedQuery<PodcastEntity> query = entityManagerLegacy.createQuery(qlString, PodcastEntity.class);		

		return query.getResultList();
	}

	public PodcastEntity getLegacyPodcastById(Long id) {
		try {
			String qlString = "SELECT p FROM PodcastEntity p WHERE p.id = ?1";
			TypedQuery<PodcastEntity> query = entityManagerLegacy.createQuery(qlString, PodcastEntity.class);		
			query.setParameter(1, id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
