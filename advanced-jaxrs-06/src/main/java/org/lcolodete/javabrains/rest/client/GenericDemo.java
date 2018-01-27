package org.lcolodete.javabrains.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class GenericDemo {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		
//		List<Message> list = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/")
//				.path("messages")
//				.queryParam("year", 2017)
//				.request(MediaType.APPLICATION_JSON)
//				.get(new GenericType<List<Message>>() {} );
//		System.out.println(list);
		
		Response response = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/")
				.path("messages")
				.queryParam("year", 2015)
				.request(MediaType.APPLICATION_JSON)
				.get();

		System.out.println(response);
		
		List<Message> returnedList = response.readEntity(new GenericType<List<Message>>() {} );
		System.out.println("returnedList="+returnedList);
	}

}
