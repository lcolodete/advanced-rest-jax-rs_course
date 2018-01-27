package org.lcolodete.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class RestApiClient {

	public static void main(String[] args) {
		
		ClientConfig config = new ClientConfig();
		config.register(LoggingFilter.class);
		
		Client client = ClientBuilder.newClient(config);
		
		//Useful for debugging!
//		String message = client
//							.target("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy?market=ES")
//							.request(MediaType.APPLICATION_JSON)
//							.get(String.class);
//		
//		System.out.println(message);
		
		
		WebTarget baseTarget = client.target("https://api.spotify.com/v1/");
		WebTarget albumsTarget = baseTarget.path("albums");
		WebTarget singleAlbumTarget = albumsTarget.path("{albumId}");
		
		Response getResponse = singleAlbumTarget
								.resolveTemplate("albumId", "4aawyAB9vmqN3uQ7FjRGTy")
								.queryParam("market", "ES")
								.request(MediaType.APPLICATION_JSON)
								.get();
		
		System.out.println(getResponse);
		
		if (getResponse.getStatus() != 200) {
			System.out.println("GET error");
			System.exit(getResponse.getStatus()*(-1));
		}
		
		String albumResponseString = getResponse.readEntity(String.class);
		
		System.out.println("====================================================================");
		
		System.out.println(albumResponseString);

//		Message fetchedMessage = getResponse.readEntity(Message.class);
//		
//		System.out.println(fetchedMessage.getMessage());

		
//		Message message1 = singleMessageTarget
//								.resolveTemplate("messageId", "1")
//								.request(MediaType.APPLICATION_JSON)
//								.get(Message.class);
//		
		
//		System.out.println(message1.getMessage());
		
		
		//do a GET request to fetch the newly created message from the server
//		Response getResponse = singleMessageTarget
//								.resolveTemplate("messageId", createdMessage.getId())
//								.request(MediaType.APPLICATION_XML)
//								.get();
		
//		if (getResponse.getStatus() != 200) {
//			System.out.println("GET error");
//			System.exit(getResponse.getStatus()*(-1));
//		}
//		
//		Message fetchedMessage = getResponse.readEntity(Message.class);
//		
//		System.out.println(getResponse);
//		System.out.println(fetchedMessage.getMessage());
	}

}
