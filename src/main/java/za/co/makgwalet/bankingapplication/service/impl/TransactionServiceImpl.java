package za.co.makgwalet.bankingapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.makgwalet.bankingapplication.model.Account;
import za.co.makgwalet.bankingapplication.model.Transaction;
import za.co.makgwalet.bankingapplication.model.TransactionType;
import za.co.makgwalet.bankingapplication.repository.TransactionRepository;
import za.co.makgwalet.bankingapplication.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Override
    public void makeTransaction(Account sourceAccount, Account destinationAccount, BigDecimal amount, TransactionType transactionType){
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(transactionType);
        transactionRepo.save(transaction);
    }

    // Other transaction-related business logic, such as getting transaction history, etc.

}
