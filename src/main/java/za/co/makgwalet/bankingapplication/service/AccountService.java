package za.co.makgwalet.bankingapplication.service;

import za.co.makgwalet.bankingapplication.model.Account;
import za.co.makgwalet.bankingapplication.model.AccountType;
import za.co.makgwalet.bankingapplication.model.Customer;

public interface AccountService {

    Account createAccount(Customer customer, AccountType accountType);
}
