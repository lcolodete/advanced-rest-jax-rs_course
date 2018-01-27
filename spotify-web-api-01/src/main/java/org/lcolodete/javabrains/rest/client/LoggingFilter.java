package org.lcolodete.javabrains.rest.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("REQUEST");
		System.out.println("HEADERS="+requestContext.getHeaders());
	}
	
	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		System.out.println("RESPONSE");
		System.out.println("HEADERS="+responseContext.getHeaders());
	}


}
