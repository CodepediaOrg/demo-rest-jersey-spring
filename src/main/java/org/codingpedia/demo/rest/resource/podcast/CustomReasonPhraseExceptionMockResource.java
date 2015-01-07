package org.codingpedia.demo.rest.resource.podcast;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.codingpedia.demo.rest.errorhandling.CustomReasonPhraseException;
import org.codingpedia.demo.rest.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/mocked-custom-reason-phrase-exception")
public class CustomReasonPhraseExceptionMockResource {
	
	@Autowired
	private PodcastService podcastService;
	
	@GET
	public void testReasonChangedInResponse() throws CustomReasonPhraseException{
		podcastService.generateCustomReasonPhraseException();
	}
}
