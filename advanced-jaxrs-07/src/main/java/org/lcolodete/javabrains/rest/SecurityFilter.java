package org.lcolodete.javabrains.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String SECURED_URL_PREFIX = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			
			List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
			if (null != authHeaders && authHeaders.size() > 0) {
				String authHeader = authHeaders.get(0);
				authHeader = authHeader.replaceFirst("Basic ", "");
				String authToken = Base64.decodeAsString(authHeader);
				String user = authToken.split(":")[0];
				String password = authToken.split(":")[1];
				
				if ("user1".equals(user) && "password".equals(password)) {
					return;
				}
			}
			
			Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the resource.")
					.build();
			
			requestContext.abortWith(unauthorizedResponse);
		}
	}

}
