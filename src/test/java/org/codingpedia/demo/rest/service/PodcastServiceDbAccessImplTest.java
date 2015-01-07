package org.codingpedia.demo.rest.service;

import static org.mockito.Mockito.*;

import org.codingpedia.demo.rest.dao.PodcastDao;
import org.codingpedia.demo.rest.dao.PodcastEntity;
import org.codingpedia.demo.rest.errorhandling.AppException;
import org.codingpedia.demo.rest.resource.podcast.Podcast;
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
	private static final Long SOME_ID = 13L;
	
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
		
		verify(podcastDao).getPodcastByFeed(SOME_FEED);//verifies if the method podcastDao.getPodcastByFeed has been called exactly once with that exact input parameter
		verify(podcastDao, times(1)).getPodcastByFeed(SOME_FEED);//same as above
		verify(podcastDao, times(1)).getPodcastByFeed(eq(SOME_FEED));//same as above
		verify(podcastDao, times(1)).getPodcastByFeed(anyString());//verifies if the method podcastDao.getPodcastByFeed has been called exactly once with any string as input
		verify(podcastDao, atLeastOnce()).getPodcastByFeed(SOME_FEED);//verifies if the method podcastDao.getPodcastByFeed has been called at least once with that exact input parameter		
		verify(podcastDao, atLeast(1)).getPodcastByFeed(SOME_FEED);//verifies if the method podcastDao.getPodcastByFeed has been called at least once with that exact input parameter
		verify(podcastDao, times(1)).createPodcast(any(PodcastEntity.class));
		verify(podcastDao, never()).getLegacyPodcastById(anyLong());//verifies the method podcastDao.getLegacyPodcastById has never been called
		
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
	

	@Test
	public void testUpdatePartiallyPodcast_successful() throws AppException {
		
		PodcastEntity podcastEntity = new PodcastEntity();
		podcastEntity.setId(SOME_ID);
		when(podcastDao.getPodcastById(SOME_ID)).thenReturn(podcastEntity);		
		doNothing().when(podcastDao).updatePodcast(any(PodcastEntity.class));
		
		Podcast podcast = new Podcast(podcastEntity);
		podcast.setFeed(SOME_FEED);
		podcast.setTitle(SOME_TITLE);
		sut.updatePartiallyPodcast(podcast);
		
		verify(podcastDao).getPodcastById(SOME_ID);//verifies if the method podcastDao.getPodcastById has been called exactly once with that exact input parameter
		verify(podcastDao).updatePodcast(any(PodcastEntity.class));		
		
		Assert.assertTrue(podcast.getFeed() == SOME_FEED);
		Assert.assertTrue(podcast.getTitle() == SOME_TITLE);
	}
	
	@Test
	public void testUpdatePartiallyPodcast_not_existing_podcast() {
		
		when(podcastDao.getPodcastById(SOME_ID)).thenReturn(null);
		
		Podcast podcast = new Podcast();
		podcast.setId(SOME_ID);
		try {
			sut.updatePartiallyPodcast(podcast);
			Assert.fail("Should have thrown an exception"); 
		} catch (AppException e) {
			verify(podcastDao).getPodcastById(SOME_ID);//verifies if the method podcastDao.getPodcastById has been called exactly once with that exact input parameter
			Assert.assertEquals(e.getCode(), 404);
		}
		
	}	
	
}
