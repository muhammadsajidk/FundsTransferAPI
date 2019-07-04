package com.fundstransfer.dao.impl;

import com.fundstransfer.dao.FundsTransferDao;
import com.fundstransfer.model.Account;
import com.fundstransfer.model.Transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */
public class FundsTransferDaoImpl implements FundsTransferDao {

	private static FundsTransferDaoImpl fundsTransferDao;
	private static Map<Long, Transaction> transactionPool;
	private static Map<String, Account> accountPool;
	private final static Object lock = new Object();

	private FundsTransferDaoImpl() {
	}

	public static FundsTransferDaoImpl getInstance() {
		if (null == fundsTransferDao) {
			synchronized (lock) {
				if (null == fundsTransferDao) {
					fundsTransferDao = new FundsTransferDaoImpl();
				}
			}
		}
		return fundsTransferDao;
	}

	public static Map<Long, Transaction> initializeTransactionPool() {
		if (null == transactionPool) {
			synchronized (lock) {
				transactionPool = new ConcurrentHashMap<>();
			}
		}
		return transactionPool;
	}

	public static Map<String, Account> initializeAccountPool() {
		if (null == accountPool) {
			synchronized (lock) {
				accountPool = new ConcurrentHashMap<>();
			}
		}
		return accountPool;
	}

	public boolean saveTransaction(Transaction transaction) {
		if (null == transactionPool.get(transaction.getTransactionId())) {
			transactionPool.put(transaction.getTransactionId(), transaction);
			return true;
		}
		else {
			return false;
		}
	}

	public static void addAccount(Account account) {
		accountPool.putIfAbsent(account.getAccountNo(), account);
	}
	public Account getAccount(String accountNo) {
		return accountPool.get(accountNo);
	}

}
