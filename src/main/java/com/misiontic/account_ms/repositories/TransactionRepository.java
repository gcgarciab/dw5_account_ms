package com.misiontic.account_ms.repositories;

import com.misiontic.account_ms.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository <Transaction, String> {
    List<Transaction> findByUserIdOrigin(String userId);
    List<Transaction> findByUserIdDestiny(String userId);
}
