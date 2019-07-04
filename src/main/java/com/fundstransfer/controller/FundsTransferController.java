package com.fundstransfer.controller;

import com.fundstransfer.model.Transaction;
import com.fundstransfer.model.TransactionVO;
import com.fundstransfer.service.FundsTransferService;
import com.fundstransfer.service.impl.FundsTransferServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */

@Path("/api/fundstransfer")
public class FundsTransferController {

	private FundsTransferService fundsTransferService = new FundsTransferServiceImpl();


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response welcome() {
		return Response.status(200).entity("Funds Transfer API is up").build();
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response processTransaction(Transaction transaction)  {
		Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		try {
			TransactionVO transactionVO = fundsTransferService.processTransaction(new TransactionVO(transaction));
			if (transactionVO.isProcessed()) {
				response = Response.status(Response.Status.OK).entity(transaction).build();
			}
			else {
				response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(transactionVO.getTransDescription()).build();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
