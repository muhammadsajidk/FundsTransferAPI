package com.fundstransfer.service;

import com.fundstransfer.model.TransactionVO;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public interface FundsTransferService {
	TransactionVO processTransaction(TransactionVO transactionVO);
}
