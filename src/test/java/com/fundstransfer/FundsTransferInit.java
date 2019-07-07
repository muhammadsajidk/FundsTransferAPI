package com.fundstransfer;

import com.fundstransfer.dao.impl.FundsTransferDaoImpl;
import com.fundstransfer.model.Account;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Currency;

import static com.fundstransfer.util.CommonConstants.DEFAULT_PORT;
import static com.fundstransfer.util.CommonConstants.TRANSACTION_CONTROLLER;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public class FundsTransferInit {

	private HttpServer httpServer;

	public static void main(String[] args) {
		try {
			FundsTransferInit fundsTransferInit = new FundsTransferInit();
			fundsTransferInit.init();
			System.out.println("Server is up and listening at port 8080");
		}
		catch (Exception e) {
			System.out.println("Exception occured while intializing FundsTransferAPI " + e.getMessage());
			System.exit(-1);
		}
	}

	public void init() throws IOException {
		FundsTransferDaoImpl.initializeTransactionPool();
		FundsTransferDaoImpl.initializeAccountPool();
		initializeServer();
		accountDataCreation();
	}

	private void initializeServer() throws IOException {
		httpServer = createHttpServer();
		httpServer.start();
	}

    public void shutdownServer() {
        httpServer.stop(0);
    }

	private HttpServer createHttpServer() throws IOException {
		ResourceConfig resourceConfig = new PackagesResourceConfig(TRANSACTION_CONTROLLER);
		return HttpServerFactory.create(getURI(), resourceConfig);
	}

	private URI getURI() throws UnknownHostException {
		return UriBuilder.fromUri("http://" + InetAddress.getLocalHost().getCanonicalHostName() + "/").port(DEFAULT_PORT).build();
	}

	private void accountDataCreation(){
		Account account = new Account("123456",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
		account = new Account("123456",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
		account = new Account("789456",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
		account = new Account("987456",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
		account = new Account("357951",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
		account = new Account("167973",100.00, Currency.getInstance("EUR"), true);
		FundsTransferDaoImpl.addAccount(account);
	}

}
