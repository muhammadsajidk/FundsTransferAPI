package com.fundstransfer.dao;

import com.fundstransfer.model.Account;
import com.fundstransfer.model.Transaction;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public interface FundsTransferDao {
	boolean saveTransaction(Transaction transaction);
	Account getAccount(String accountNo);
}
