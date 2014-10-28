package org.codingpedia.demo.rest;

import java.lang.annotation.Annotation;

import org.codingpedia.demo.rest.errorhandling.AppExceptionMapper;
import org.codingpedia.demo.rest.errorhandling.CustomReasonPhraseExceptionMapper;
import org.codingpedia.demo.rest.errorhandling.GenericExceptionMapper;
import org.codingpedia.demo.rest.errorhandling.NotFoundExceptionMapper;
import org.codingpedia.demo.rest.filters.CORSResponseFilter;
import org.codingpedia.demo.rest.filters.LoggingResponseFilter;
import org.codingpedia.demo.rest.resource.PodcastDetailedView;
import org.codingpedia.demo.rest.resource.PodcastLegacyResource;
import org.codingpedia.demo.rest.resource.PodcastsResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author ama
 * 
 */
public class RestDemoJaxRsApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public RestDemoJaxRsApplication() {
		
        packages("org.codingpedia.demo.rest");
        
//		// register application resources
//		register(PodcastsResource.class);
//		register(PodcastLegacyResource.class);
//
//		// register filters
//		register(RequestContextFilter.class);
//		register(LoggingResponseFilter.class);
//		register(CORSResponseFilter.class);
//
//		// register exception mappers
//		register(GenericExceptionMapper.class);
//		register(AppExceptionMapper.class);
//      register(CustomReasonPhraseExceptionMapper.class);
//		register(NotFoundExceptionMapper.class);
//
//		// register features
//		register(JacksonFeature.class);
		register(EntityFilteringFeature.class);
		
//		property(EntityFilteringFeature.ENTITY_FILTERING_SCOPE, new Annotation[] {PodcastDetailedView.Factory.get()});
	}
}
