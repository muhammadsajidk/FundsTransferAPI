package com.fundstransfer.service.impl;

import com.fundstransfer.dao.FundsTransferDao;
import com.fundstransfer.dao.impl.FundsTransferDaoImpl;
import com.fundstransfer.model.Account;
import com.fundstransfer.model.Transaction;
import com.fundstransfer.model.TransactionVO;
import com.fundstransfer.service.FundsTransferService;
import com.fundstransfer.util.CommonConstants;

/**
 * @author Muhammad Sajid
 * @since July, 02 2019
 */

public class FundsTransferServiceImpl implements FundsTransferService {

	private FundsTransferDao fundsTransferDao = FundsTransferDaoImpl.getInstance();

	public TransactionVO processTransaction(TransactionVO transactionVO) {
		Transaction transaction = transactionVO.getTransaction();
		// 1 - Fetch Accounts
		Account fromAccount = fetchAccount(transaction.getFromAccount());
		Account toAccount = fetchAccount(transaction.getToAccount());

		if (isValid(transactionVO, fromAccount, toAccount)) {
			// 2 - Process Transaction
			synchronized (fromAccount){
				if (process(transactionVO, fromAccount, toAccount)) {
					// 3 - Commit Transaction
					commitTransaction(transaction);
				}
			}
		}
		return transactionVO;
	}

	private boolean isValid(TransactionVO transactionVO, Account fromAccount, Account toAccount) {
		boolean isValid = true;
		if (null == fromAccount || null == toAccount) {
			transactionVO.setTransDescription("From account or To account is not valid.");
			transactionVO.setProcessed(false);
			isValid = false;
		}
		else if (CommonConstants.ACTIVE_STATUS != fromAccount.accountStatus()) {
			transactionVO.setTransDescription("From account is in-active.");
			transactionVO.setProcessed(false);
			isValid = false;
		}
		else if (fromAccount.getAccountNo().equals(toAccount.getAccountNo())) {
			transactionVO.setTransDescription("Same account transaction is not allowed.");
			transactionVO.setProcessed(false);
			isValid = false;
		}
		else if (Double.compare(transactionVO.getTransaction().getAmount(),0D)<=0){
			transactionVO.setTransDescription("Transaction amount can negative or 0.");
			transactionVO.setProcessed(false);
			isValid = false;
		}
		return isValid;
	}

	private boolean process(TransactionVO transactionVO, Account fromAccount, Account toAccount) {

		double fromAccountBalance = fromAccount.getBalance();
		double toAccountBalance = toAccount.getBalance();
		double transactionAmount = transactionVO.getTransaction().getAmount();

		if (Double.compare(fromAccountBalance, transactionAmount) > 0D) {
			fromAccount.setBalance(fromAccountBalance - transactionAmount);
			toAccount.setBalance(toAccountBalance + transactionAmount);
			transactionVO.setProcessed(true);
			transactionVO.setTransDescription(CommonConstants.SUCCESS_DESCRIPTION);
		}
		else {
			transactionVO.setProcessed(false);
			transactionVO.setTransDescription(CommonConstants.INSUFFICIENT_FUNDS_DESCRIPTION);
		}
		return transactionVO.isProcessed();
	}

	private Account fetchAccount(String accountNo) {
		return fundsTransferDao.getAccount(accountNo);
	}

	private boolean commitTransaction(Transaction transaction) {
		return fundsTransferDao.saveTransaction(transaction);
	}
}
