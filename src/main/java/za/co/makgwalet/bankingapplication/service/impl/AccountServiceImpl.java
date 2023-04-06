package za.co.makgwalet.bankingapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.makgwalet.bankingapplication.exception.InsufficientBalanceException;
import za.co.makgwalet.bankingapplication.model.Account;
import za.co.makgwalet.bankingapplication.model.AccountType;
import za.co.makgwalet.bankingapplication.model.Customer;
import za.co.makgwalet.bankingapplication.model.TransactionType;
import za.co.makgwalet.bankingapplication.repository.AccountRepository;
import za.co.makgwalet.bankingapplication.service.AccountService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public Account createAccount(Customer customer, AccountType accountType){
        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountNumber(generateAccountNumber(accountType));
        account.setAccountType(accountType);
        if(accountType == AccountType.SAVINGS)
            account.setBalance(BigDecimal.valueOf(500.00));
        else
            account.setBalance(BigDecimal.valueOf(0.0));

        return accountRepo.save(account);
    }


    /*
    * Generating an account number of 13 digits.
    * if the account type is savings, the account number starts with 62 else it starts with 63
    * checking if the account exists, if it exists, create a new one.
    * */
    public String generateAccountNumber(AccountType accountType){
        String generatedAccountNumber;
        long randomNumber;
        Random random = new Random();
        do{
            if(accountType == AccountType.SAVINGS)
                randomNumber = (random.nextLong() % 9000000000000L) + 62000000000000L;
            else
                randomNumber = (random.nextLong() % 9000000000000L) + 63000000000000L;

            generatedAccountNumber = String.valueOf(randomNumber);

        }
        while (accountRepo.findByAccountNumber(generatedAccountNumber).isPresent());
        return generatedAccountNumber;
    }

    // Other account-related business logic, such as getting account details, making payments, etc.
    public void transferFunds(String sourceAccountNumber,String destinationAccountNumber, BigDecimal amount) {

        if(accountRepo.findByAccountNumber(sourceAccountNumber).isEmpty())
            throw new IllegalStateException();

        if(accountRepo.findByAccountNumber(destinationAccountNumber).isEmpty())
            throw new IllegalStateException();

        Account  destinationAccount = accountRepo.findByAccountNumber(destinationAccountNumber).get();
        Account sourceAccount = accountRepo.findByAccountNumber(sourceAccountNumber).get();

        // Validate transaction amount
        if (amount.doubleValue()<= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
        // Deduct amount from source account
        if (sourceAccount.getBalance().doubleValue() < amount.doubleValue()) {
            throw new InsufficientBalanceException("Insufficient balance in source account.");
        }
        sourceAccount.setBalance(BigDecimal.valueOf(sourceAccount.getBalance().doubleValue() - amount.doubleValue()));
        // Credit amount to destination account
        destinationAccount.setBalance(BigDecimal.valueOf(destinationAccount.getBalance().doubleValue() + amount.doubleValue()));
        // Store transaction details in Transaction entity
        /*Transaction transaction = new Transaction(sourceAccount, destinationAccount, amount, TransactionType.TRANSFER);
        transactionRepository.save(transaction);*/
    }
}
