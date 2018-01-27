package org.lcolodete.javabrains.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("text/shortdate")
public class ShortDateMessageBodyWriter implements MessageBodyWriter<Date> {

	@Override
	public long getSize(Date arg0, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Date date,
			Class<?> type, 
			Type genericType, 
			Annotation[] annotations, 
			MediaType mediaType, 
			MultivaluedMap<String,Object> httpHeaders, 
			OutputStream out) throws IOException, WebApplicationException {
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
		
		String shortDate = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
		
		out.write(shortDate.getBytes());
	}

}
