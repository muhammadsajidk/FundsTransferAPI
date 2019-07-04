package com.fundstransfer;

import com.fundstransfer.model.Transaction;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FundsTransferInitTest {

	private static final String URL = "http://localhost:8080";
	private static final String PATH = "/api/fundstransfer";
	private FundsTransferInit initializer = new FundsTransferInit();
	private Client client;

	@Before
	public void setUp() throws Exception {
		initializer.init();
		client = Client.create();
	}

	@After
	public void shutDown() throws Exception {
		initializer.shutdownServer();
		client.destroy();
	}

	@Test
	public void isServerUp() {
		WebResource webResource = client.resource(URL + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void processTransactionSuccess() throws Exception {
		Transaction transaction = new Transaction(1, "123456", "789456", 10.0);

		WebResource webResource = client.resource(URL + PATH + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, transaction);
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void processTransactionFailed() throws Exception {

		Transaction transaction = new Transaction(1, "123456", "0000", 10.0);

		WebResource webResource = client.resource(URL + PATH + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, transaction);
		Assert.assertEquals(response.getStatus(), 500);
	}

	@Test
	public void processTransactionInsufficientFunds() throws Exception {

		Transaction transaction = new Transaction(1, "123456", "789456", 1000.0);

		WebResource webResource = client.resource(URL + PATH + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, transaction);
		Assert.assertEquals(response.getStatus(), 500);
	}

	@Test
	public void processTransactionNegativeTransAmount() throws Exception {

		Transaction transaction = new Transaction(1, "123456", "789456", -25.0);

		WebResource webResource = client.resource(URL + PATH + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, transaction);
		Assert.assertEquals(response.getStatus(), 500);
	}

	@Test
	public void processTransactionZeroTransAmount() throws Exception {

		Transaction transaction = new Transaction(1, "123456", "789456", 0.0);

		WebResource webResource = client.resource(URL + PATH + "/");
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, transaction);
		Assert.assertEquals(response.getStatus(), 500);
	}
}
