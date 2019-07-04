package com.fundstransfer.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider public class CustomExceptionMapper extends Throwable implements ExceptionMapper<CustomExceptionMapper> {

	@Override public Response toResponse(CustomExceptionMapper ex) {
		return Response.status(404).entity("generic expcetion").build();
	}
}