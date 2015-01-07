package org.codingpedia.demo.rest.resource.podcast;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codingpedia.demo.rest.errorhandling.AppException;
import org.codingpedia.demo.rest.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Service class that handles REST requests
 * 
 * @author amacoder
 * 
 */
@Component
@Path("/legacy/podcasts")
public class PodcastLegacyResource {

	@Autowired
	private PodcastService podcastService;

	/************************************ READ ************************************/
	/**
	 * Returns all resources (podcasts) from the database
	 * 
	 * @return
	 * @throws AppException 
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Podcast> getPodcasts() throws AppException {
		return podcastService.getLegacyPodcasts();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findById(@PathParam("id") Long id) throws AppException {		
		Podcast podcastById = podcastService.getLegacyPodcastById(id);
		if (podcastById != null) {
			return Response.status(200).entity(podcastById).build();
		} else {
			String message = "The podcast with the id " + id + " does not exist"; 
			throw new AppException(404, 4004, message, message, "link");
		}
	}

}
