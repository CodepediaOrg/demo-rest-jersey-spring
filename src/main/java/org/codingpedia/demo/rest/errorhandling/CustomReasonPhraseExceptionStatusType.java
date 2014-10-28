package org.codingpedia.demo.rest.errorhandling;

import javax.ws.rs.core.Response.Status;

/**
 * Implementation of StatusType for CustomReasonPhraseException.
 * The Reason Phrase is set in this case to "Custom error message"
 */
public class CustomReasonPhraseExceptionStatusType extends AbstractStatusType{
	
	private static final String CUSTOM_EXCEPTION_REASON_PHRASE = "Custom error message";
	
	public CustomReasonPhraseExceptionStatusType(Status httpStatus) {
		super(httpStatus, CUSTOM_EXCEPTION_REASON_PHRASE);
	}

}
