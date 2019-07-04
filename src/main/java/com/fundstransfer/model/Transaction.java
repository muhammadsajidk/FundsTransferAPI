package com.fundstransfer.model;

import java.io.Serializable;

import static com.fundstransfer.util.CommonConstants.COUNTER;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public class Transaction implements Serializable {

	private long transactionId;
	private String fromAccount;
	private String toAccount;
	private double amount;



	public Transaction(long transactionId, String fromAccount, String toAccount, double amount) {
		this.transactionId = COUNTER.getAndIncrement();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public long getTransactionId() {
		return transactionId;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public double getAmount() {
		return amount;
	}
}
