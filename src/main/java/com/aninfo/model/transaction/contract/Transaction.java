package com.aninfo.model.transaction.contract;

import com.aninfo.model.TransactionEntity;

public interface Transaction {
	TransactionEntity execute(TransactionEntity transaction);
}
