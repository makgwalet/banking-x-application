package za.co.makgwalet.bankingapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.makgwalet.bankingapplication.model.Account;
import za.co.makgwalet.bankingapplication.model.AccountType;
import za.co.makgwalet.bankingapplication.model.Customer;
import za.co.makgwalet.bankingapplication.repository.CustomerRepository;
import za.co.makgwalet.bankingapplication.service.AccountService;
import za.co.makgwalet.bankingapplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private AccountService  accountService;

    @Override
    public Customer createCustomer(Customer customer) {

        Account savingsAccount = accountService.createAccount(customer, AccountType.SAVINGS);
        Account currentAccount = accountService.createAccount(customer,AccountType.CURRENT);
        return customerRepo.save(customer);
    }

    // Other customer-related business logic, such as getting customer details, updating customer information, etc.

}
