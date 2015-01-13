package org.codingpedia.demo.rest.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.codingpedia.demo.rest.dao.PodcastDao;
import org.codingpedia.demo.rest.dao.PodcastEntity;
import org.codingpedia.demo.rest.errorhandling.AppException;
import org.codingpedia.demo.rest.errorhandling.CustomReasonPhraseException;
import org.codingpedia.demo.rest.filters.AppConstants;
import org.codingpedia.demo.rest.helpers.NullAwareBeanUtilsBean;
import org.codingpedia.demo.rest.resource.podcast.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PodcastServiceDbAccessImpl implements PodcastService {

	@Autowired
	PodcastDao podcastDao;
		
	/********************* Create related methods implementation ***********************/
	@Transactional("transactionManager")
	public Long createPodcast(Podcast podcast) throws AppException {
		
		validateInputForCreation(podcast);
		
		//verify existence of resource in the db (feed must be unique)
		PodcastEntity podcastByFeed = podcastDao.getPodcastByFeed(podcast.getFeed());
		if(podcastByFeed != null){
			throw new AppException(Response.Status.CONFLICT.getStatusCode(), 409, "Podcast with feed already existing in the database with the id " + podcastByFeed.getId(),
					"Please verify that the feed and title are properly generated", AppConstants.BLOG_POST_URL);
		}
		
		return podcastDao.createPodcast(new PodcastEntity(podcast));
	}

	private void validateInputForCreation(Podcast podcast) throws AppException {
		if(podcast.getFeed() == null){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the feed is properly generated/set", AppConstants.BLOG_POST_URL);
		}
		if(podcast.getTitle() == null){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Provided data not sufficient for insertion",
					"Please verify that the title is properly generated/set", AppConstants.BLOG_POST_URL);
		}
		//etc...
	}
	
	@Transactional("transactionManager")
	public void createPodcasts(List<Podcast> podcasts) throws AppException {
		for (Podcast podcast : podcasts) {
			createPodcast(podcast);
		}		
	}	
	
	
	 // ******************** Read related methods implementation **********************		
	public List<Podcast> getPodcasts(String orderByInsertionDate, Integer numberDaysToLookBack) throws AppException {
		
		//verify optional parameter numberDaysToLookBack first 
		if(numberDaysToLookBack!=null){
			List<PodcastEntity> recentPodcasts = podcastDao.getRecentPodcasts(numberDaysToLookBack);			
			return getPodcastsFromEntities(recentPodcasts);			
		}
		
		if(isOrderByInsertionDateParameterValid(orderByInsertionDate)){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Please set either ASC or DESC for the orderByInsertionDate parameter", null , AppConstants.BLOG_POST_URL);
		}			
		List<PodcastEntity> podcasts = podcastDao.getPodcasts(orderByInsertionDate);
		
		return getPodcastsFromEntities(podcasts);
	}

	private boolean isOrderByInsertionDateParameterValid(
			String orderByInsertionDate) {
		return orderByInsertionDate!=null 
				&& !("ASC".equalsIgnoreCase(orderByInsertionDate) || "DESC".equalsIgnoreCase(orderByInsertionDate));
	}
	
	public Podcast getPodcastById(Long id) throws AppException {		
		PodcastEntity podcastById = podcastDao.getPodcastById(id);
		if(podcastById == null){
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The podcast you requested with id " + id + " was not found in the database",
					"Verify the existence of the podcast with the id " + id + " in the database",
					AppConstants.BLOG_POST_URL);			
		}
		
		return new Podcast(podcastDao.getPodcastById(id));
	}	

	private List<Podcast> getPodcastsFromEntities(List<PodcastEntity> podcastEntities) {
		List<Podcast> response = new ArrayList<Podcast>();
		for(PodcastEntity podcastEntity : podcastEntities){
			response.add(new Podcast(podcastEntity));					
		}
		
		return response;
	}

	public List<Podcast> getRecentPodcasts(int numberOfDaysToLookBack) {
		List<PodcastEntity> recentPodcasts = podcastDao.getRecentPodcasts(numberOfDaysToLookBack);
		
		return getPodcastsFromEntities(recentPodcasts);
	}

	public List<Podcast> getLegacyPodcasts() {
		List<PodcastEntity> legacyPodcasts = podcastDao.getLegacyPodcasts();
		
		return getPodcastsFromEntities(legacyPodcasts);
	}

	public Podcast getLegacyPodcastById(Long id) {
		return new Podcast(podcastDao.getLegacyPodcastById(id));
	}
	
	
	/********************* UPDATE-related methods implementation ***********************/	
	@Transactional("transactionManager")
	public void updateFullyPodcast(Podcast podcast) throws AppException {
		//do a validation to verify FULL update with PUT
		if(isFullUpdate(podcast)){
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 
					400, 
					"Please specify all properties for Full UPDATE",
					"required properties - id, title, feed, lnkOnPodcastpedia, description" ,
					AppConstants.BLOG_POST_URL);			
		}
		
		Podcast verifyPodcastExistenceById = verifyPodcastExistenceById(podcast.getId());
		if(verifyPodcastExistenceById == null){
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - " + podcast.getId(),
					AppConstants.BLOG_POST_URL);				
		}
				
		podcastDao.updatePodcast(new PodcastEntity(podcast));
	}

	/**
	 * Verifies the "completeness" of podcast resource sent over the wire
	 * 
	 * @param podcast
	 * @return
	 */
	private boolean isFullUpdate(Podcast podcast) {
		return podcast.getId() == null
				|| podcast.getFeed() == null
				|| podcast.getLinkOnPodcastpedia() == null
				|| podcast.getTitle() == null
				|| podcast.getDescription() == null;
	}
	
	/********************* DELETE-related methods implementation ***********************/
	@Transactional("transactionManager")
	public void deletePodcastById(Long id) {
		podcastDao.deletePodcastById(id);
	}
	
	@Transactional("transactionManager")	
	public void deletePodcasts() {
		podcastDao.deletePodcasts();		
	}

	public Podcast verifyPodcastExistenceById(Long id) {
		PodcastEntity podcastById = podcastDao.getPodcastById(id);
		if(podcastById == null){
			return null;
		} else {
			return new Podcast(podcastById);			
		}
	}

	@Transactional("transactionManager")
	public void updatePartiallyPodcast(Podcast podcast) throws AppException {
		//do a validation to verify existence of the resource		
		Podcast verifyPodcastExistenceById = verifyPodcastExistenceById(podcast.getId());
		if(verifyPodcastExistenceById == null){
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The resource you are trying to update does not exist in the database",
					"Please verify existence of data in the database for the id - " + podcast.getId(),
					AppConstants.BLOG_POST_URL);				
		}
		copyPartialProperties(verifyPodcastExistenceById, podcast);		
		podcastDao.updatePodcast(new PodcastEntity(verifyPodcastExistenceById));
		
	}

	private void copyPartialProperties(Podcast verifyPodcastExistenceById,
						Podcast podcast) {
		
		BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(verifyPodcastExistenceById, podcast);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void generateCustomReasonPhraseException() throws CustomReasonPhraseException {		
		throw new CustomReasonPhraseException(4000, "message attached to the Custom Reason Phrase Exception");		
	}

	public void setPodcastDao(PodcastDao podcastDao) {
		this.podcastDao = podcastDao;
	}
		
}
