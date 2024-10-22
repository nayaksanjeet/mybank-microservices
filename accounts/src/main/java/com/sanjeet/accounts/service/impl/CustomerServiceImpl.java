package com.sanjeet.accounts.service.impl;

import com.sanjeet.accounts.dto.AccountsDto;
import com.sanjeet.accounts.dto.CardsDto;
import com.sanjeet.accounts.dto.CustomerDetailsDto;
import com.sanjeet.accounts.dto.LoanDto;
import com.sanjeet.accounts.entity.Accounts;
import com.sanjeet.accounts.entity.Customer;
import com.sanjeet.accounts.exception.ResourceNotFoundException;
import com.sanjeet.accounts.mapper.AccountsMapper;
import com.sanjeet.accounts.mapper.CustomerMapper;
import com.sanjeet.accounts.repository.AccountsRepository;
import com.sanjeet.accounts.repository.CustomerRepository;
import com.sanjeet.accounts.service.client.CardsFeignClient;
import com.sanjeet.accounts.service.client.ICustomerService;
import com.sanjeet.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNUmber", mobileNumber.toString())
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapCustomerToDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto( AccountsMapper.mapAccountsToDto(accounts, new AccountsDto()));
        ResponseEntity<LoanDto> loanDto = loansFeignClient.fetchLoan(customerDetailsDto.getMobileNumber());
        if(loanDto.getBody() != null)
            customerDetailsDto.setLoanDto(loanDto.getBody());
        ResponseEntity<CardsDto> cardsDto = cardsFeignClient.fetchCardDetails(customerDetailsDto.getMobileNumber());
        if(cardsDto.getBody() != null)
            customerDetailsDto.setCardsDto(cardsDto.getBody());
        return customerDetailsDto;
    }
}