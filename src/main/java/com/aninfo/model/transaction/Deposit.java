package com.aninfo.model.transaction;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Account;
import com.aninfo.model.TransactionEntity;
import com.aninfo.model.transaction.contract.Transaction;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Deposit implements Transaction {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountService accountService;

	public TransactionEntity execute(TransactionEntity transaction) {
		Double transactionAmount = transaction.getAmount();
		if(transactionAmount <= 0) {
			throw new DepositNegativeSumException("Cannot deposit negative sums");
		}
		Account account = accountService.findById(transaction.getCbu()).orElseThrow(() ->
				new InvalidTransactionTypeException("cbu not found"));

		account.setBalance(account.getBalance() + transaction.getAmount());
		return transactionRepository.save(transaction);
	}
}