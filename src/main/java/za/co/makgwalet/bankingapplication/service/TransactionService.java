package za.co.makgwalet.bankingapplication.service;

import za.co.makgwalet.bankingapplication.model.Account;
import za.co.makgwalet.bankingapplication.model.Transaction;
import za.co.makgwalet.bankingapplication.model.TransactionType;

import java.math.BigDecimal;

public interface TransactionService {

    void makeTransaction(Account sourceAccount, Account destinationAccount, BigDecimal amount, TransactionType transactionType);
}
