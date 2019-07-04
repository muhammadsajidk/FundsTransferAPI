package com.fundstransfer.model;

/**
 * @author Muhammad Sajid
 * @since July, 03 2019
 */
public class TransactionVO {

	private Transaction transaction;
	private boolean isProcessed;
	private String transDescription;

	public TransactionVO(Transaction transaction) {
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}
	public boolean isProcessed() {
		return isProcessed;
	}
	public void setProcessed(boolean processed) {
		isProcessed = processed;
	}
	public String getTransDescription() {
		return transDescription;
	}
	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}
}
