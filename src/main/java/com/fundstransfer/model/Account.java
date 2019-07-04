package com.fundstransfer.model;

import java.util.Currency;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */

public class Account {

	private String accountNo;
	private double balance;
	private Currency currency;
	private boolean accountStatus;

	public Account(String accountNo, double balance, Currency currency, boolean accountStatus) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.currency = currency;
		this.accountStatus = accountStatus;
	}

	public String getAccountNo() {
		return accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean accountStatus() {
		return accountStatus;
	}
}
