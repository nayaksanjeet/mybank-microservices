package com.sanjeet.accounts.service.impl;

import com.sanjeet.accounts.constants.AccountsConstants;
import com.sanjeet.accounts.dto.AccountsDto;
import com.sanjeet.accounts.dto.CustomerDto;
import com.sanjeet.accounts.entity.Accounts;
import com.sanjeet.accounts.entity.Customer;
import com.sanjeet.accounts.exception.CustomerAlreadyExistsException;
import com.sanjeet.accounts.exception.ResourceNotFoundException;
import com.sanjeet.accounts.mapper.AccountsMapper;
import com.sanjeet.accounts.mapper.CustomerMapper;
import com.sanjeet.accounts.repository.AccountsRepository;
import com.sanjeet.accounts.repository.CustomerRepository;
import com.sanjeet.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapDtoToCustomer(customerDto, new Customer());
        if (customerRepository.findByMobileNumber(customer.getMobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number " + customer.getMobileNumber());
        }
       // customer.setCreatedBy("Anonymous");
      //  customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
        //create account
        //update customer
    }

    @Override
    public CustomerDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNUmber", mobileNumber.toString())
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapCustomerToDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapAccountsToDto(accounts, new AccountsDto()));
        return customerDto;

    }


    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1000000000L + new Random().nextInt(900000000));
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
       // accounts.setCreatedBy("Anonymous");
       // accounts.setCreatedAt(LocalDateTime.now());
        return accounts;
        //return accountsRepository.save(accounts);
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("account", "accountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapAccountDtoToAccounts(accountsDto, accounts);
            Accounts saveAccounts = accountsRepository.save(accounts);
            Customer customer = customerRepository.findById(saveAccounts.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("customer", "customerId", saveAccounts.getCustomerId().toString())
            );
            CustomerMapper.mapDtoToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNUmber", mobileNumber.toString())
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;

    }
}