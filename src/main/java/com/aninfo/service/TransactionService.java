package com.aninfo.service;

import com.aninfo.model.TransactionEntity;
import com.aninfo.model.transaction.Deposit;
import com.aninfo.model.transaction.Withdraw;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private Deposit deposit;
	@Autowired
	private Withdraw withdraw;


	public Collection<TransactionEntity> getTransactions(Long cbu) {
		return transactionRepository.findTransactionsByCbu(cbu);
	}

	public TransactionEntity getTransaction(Long transactionId) {
		return transactionRepository.findTransactionByTransactionId(transactionId);
	}

	public TransactionEntity createDeposit(TransactionEntity transaction) {
		return deposit.execute(transaction);
	}

	public void deleteTransaction(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

	public TransactionEntity createWithdraw(TransactionEntity transaction) {
		return withdraw.execute(transaction);
	}
}