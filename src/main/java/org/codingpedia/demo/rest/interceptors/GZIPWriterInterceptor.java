package org.codingpedia.demo.rest.interceptors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;


/**
 * 
 * The jersey framework has a built-in functionality to easily enable content encoding.
 * The functionality can be activated by the following code line <code>EncodingFilter.enableFor(ResourceConfig rc, GZipEncoder.class)</code>
 * 
 * You can use this <code>WriterInterceptor</code> to <b>selectively</b> compress responses on the method level. 
 */
@Provider
@Compress
public class GZIPWriterInterceptor implements WriterInterceptor {
	 
    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
                    throws IOException, WebApplicationException {
    	
    	MultivaluedMap<String,Object> headers = context.getHeaders();
    	headers.add("Content-Encoding", "gzip");
    	
        final OutputStream outputStream = context.getOutputStream();
        context.setOutputStream(new GZIPOutputStream(outputStream));
        context.proceed();
    }
}