package org.codingpedia.demo.rest.resource.version;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/version")
public class VersionResource {
	
	@Autowired
	VersionService versionService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getVersion() throws FileNotFoundException, IOException{
		return Response.status(Response.Status.CREATED)// 201
				.entity(versionService.getImplementationVersion())
				.build();
	}

}
