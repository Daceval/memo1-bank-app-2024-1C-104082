package com.aninfo.repository;

import com.aninfo.model.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
	TransactionEntity findTransactionByTransactionId(Long id);
	Collection<TransactionEntity> findTransactionsByCbu(Long id);
}
