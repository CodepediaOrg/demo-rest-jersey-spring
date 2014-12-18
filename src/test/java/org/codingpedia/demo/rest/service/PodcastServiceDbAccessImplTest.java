package org.codingpedia.demo.rest.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.codingpedia.demo.rest.dao.PodcastDao;
import org.codingpedia.demo.rest.dao.PodcastEntity;
import org.codingpedia.demo.rest.errorhandling.AppException;
import org.codingpedia.demo.rest.resource.Podcast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PodcastServiceDbAccessImplTest {

	private static final Long CREATED_PODCAST_RESOURCE_ID = Long.valueOf(1);
	private static final String SOME_FEED = "some_feed";
	private static final String SOME_TITLE = "some title";
	private static final String EXISTING_FEED = "http://quarks.de/feed";
	
	@Rule
	public ExpectedException exception = ExpectedException.none();	

	PodcastServiceDbAccessImpl sut;//system under test
	
	@Mock
	PodcastDao podcastDao;
	
	@Before
	public void setUp() throws Exception {		
		sut = new PodcastServiceDbAccessImpl();
		sut.setPodcastDao(podcastDao);
	}

	@Test
	public void testCreatePodcast_successful() throws AppException {
		
		when(podcastDao.getPodcastByFeed(SOME_FEED)).thenReturn(null);		
		when(podcastDao.createPodcast(any(PodcastEntity.class))).thenReturn(CREATED_PODCAST_RESOURCE_ID);
		
		Podcast podcast = new Podcast();
		podcast.setFeed(SOME_FEED);
		podcast.setTitle(SOME_TITLE);
		Long createPodcast = sut.createPodcast(podcast);

		Assert.assertTrue(createPodcast == CREATED_PODCAST_RESOURCE_ID);
	}

	@Test(expected=AppException.class)	
	public void testCreatePodcast_error() throws AppException {
		
		PodcastEntity existingPodcast = new PodcastEntity();
		when(podcastDao.getPodcastByFeed(EXISTING_FEED)).thenReturn(existingPodcast);			
		
		Podcast podcast = new Podcast();
		podcast.setFeed(EXISTING_FEED);
		podcast.setTitle(SOME_TITLE);
		sut.createPodcast(podcast);

	}
	
	@Test	
	public void testCreatePodcast_validation_missingFeed() throws AppException {
		
		exception.expect(AppException.class);
		exception.expectMessage("Provided data not sufficient for insertion");
						
		sut.createPodcast(new Podcast());

	}		
	
	@Test	
	public void testCreatePodcast_validation_missingTitle() throws AppException {
		
		exception.expect(AppException.class);
		exception.expectMessage("Provided data not sufficient for insertion");
						
		Podcast podcast = new Podcast();
		podcast.setFeed(EXISTING_FEED);
		sut.createPodcast(podcast);

	}	
	
}
