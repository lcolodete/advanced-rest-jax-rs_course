package org.lcolodete.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		
//		WebTarget target = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/messages/1");
//		Builder builder = target.request();
//		Response response = builder.get();
//		
//		System.out.println(response);
//		
//		Message message = response.readEntity(Message.class);
		
		
		
//		Message message = client
//				.target("http://localhost:8080/advanced-jaxrs-06/webapi/messages/1")
//				.request(MediaType.APPLICATION_JSON)
//				.get(Message.class);
//		
//		System.out.println(message.getAuthor());
//		System.out.println(message.getId());
//		System.out.println(message.getCreated());
//		System.out.println(message.getMessage());
		
		//Useful for debugging!
//		String message = client
//							.target("http://localhost:8080/advanced-jaxrs-06/webapi/messages/2")
//							.request(MediaType.APPLICATION_JSON)
//							.get(String.class);
//		
//		System.out.println(message);
		
		
		WebTarget baseTarget = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		Message message1 = singleMessageTarget
								.resolveTemplate("messageId", "1")
								.request(MediaType.APPLICATION_JSON)
								.get(Message.class);
		
		Message message2 = singleMessageTarget
								.resolveTemplate("messageId", "2")
								.request(MediaType.APPLICATION_JSON)
								.get(Message.class);
		
//		System.out.println(message1.getMessage());
//		System.out.println(message2.getMessage());
		
		//POST
		Message newMessage = new Message(0, "Message YYYYY", "lcolodete");

		Response postResponse = messagesTarget
									.request()
									.post(Entity.json(newMessage));
		
		if (postResponse.getStatus() != 201) {
			System.out.println("POST error");
			System.exit(postResponse.getStatus()*(-1));
		}
		
		Message createdMessage = postResponse.readEntity(Message.class);
		
		System.out.println(postResponse);
		System.out.println(createdMessage.getMessage());
		
		//do a GET request to fetch the newly created message from the server
		Response getResponse = singleMessageTarget
								.resolveTemplate("messageId", createdMessage.getId())
								.request(MediaType.APPLICATION_XML)
								.get();
		
		if (getResponse.getStatus() != 200) {
			System.out.println("GET error");
			System.exit(getResponse.getStatus()*(-1));
		}
		
		Message fetchedMessage = getResponse.readEntity(Message.class);
		
		System.out.println(getResponse);
		System.out.println(fetchedMessage.getMessage());
	}

}
