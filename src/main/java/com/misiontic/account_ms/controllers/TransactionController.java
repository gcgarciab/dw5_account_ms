package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.exceptions.InsufficientBalanceException;
import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.models.Transaction;
import com.misiontic.account_ms.repositories.AccountRepository;
import com.misiontic.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction) {
        Account accountOrigin = this.accountRepository.findById(transaction.getUserIdOrigin()).orElse(null);
        Account accountDestiny = this.accountRepository.findById(transaction.getUserIdDestiny()).orElse(null);

        if (accountOrigin == null) {
            throw new AccountNotFoundException("No se encontro una cuenta con el userId: " + transaction.getUserIdOrigin());
        }

        if (accountDestiny == null) {
            throw new AccountNotFoundException("No se encontro una cuenta con el userId: " + transaction.getUserIdDestiny());
        }

        if (accountOrigin.getBalance() < transaction.getValue()) {
            throw new InsufficientBalanceException("Fondos insuficientes!!!");
        }

        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountOrigin.setLastChange(new Date());
        this.accountRepository.save(accountOrigin);

        accountDestiny.setBalance(accountDestiny.getBalance() + transaction.getValue());
        accountDestiny.setLastChange(new Date());
        this.accountRepository.save(accountDestiny);

        transaction.setDate(new Date());
        return this.transactionRepository.save(transaction);
    }

    @GetMapping("/transactions/{userId}")
    List<Transaction> userTransactions(@PathVariable String userId) {
        Account userAccount = this.accountRepository.findById(userId).orElse(null);

        if (userAccount == null) {
            throw new AccountNotFoundException("No se encontro una cuenta con el userId: " + userId);
        }

        List<Transaction> transactionsOrigin = this.transactionRepository.findByUserIdOrigin(userId);
        List<Transaction> transactionsDestiny = this.transactionRepository.findByUserIdDestiny(userId);

        List<Transaction> transactions = Stream.concat(transactionsOrigin.stream(), transactionsDestiny.stream()).collect(Collectors.toList());
        return transactions;
    }
}
