package com.fundstransfer.util;

import com.sun.net.httpserver.HttpServer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public interface CommonConstants {

	AtomicInteger COUNTER = new AtomicInteger();
	boolean ACTIVE_STATUS = true;

	String TRANSACTION_CONTROLLER = "com.fundstransfer.controller";
	int DEFAULT_PORT = 8080;

	String SUCCESS_DESCRIPTION = "Funds Transfer Successful";
	String INSUFFICIENT_FUNDS_DESCRIPTION = "Insufficient Balance";
	String SERVICE_UNAVAILABLE_MSG = "Service unavailable due to some technical difficulties.";
}
