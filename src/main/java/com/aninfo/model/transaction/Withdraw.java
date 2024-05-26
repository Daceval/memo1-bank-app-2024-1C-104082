package com.aninfo.model.transaction;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Account;
import com.aninfo.model.TransactionEntity;
import com.aninfo.model.transaction.contract.Transaction;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Withdraw implements Transaction {
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionRepository transactionRepository;

	public TransactionEntity execute(TransactionEntity transaction) {
		Account account = accountService.findById(transaction.getCbu()).orElseThrow(() ->
				new InvalidTransactionTypeException("cbu not found"));

		if(transaction.getAmount() > account.getBalance()) {
			throw new InsufficientFundsException("Insufficient funds");
		}
		account.setBalance(account.getBalance() - transaction.getAmount());
		return transactionRepository.save(transaction);
	}
}