package com.sanjeet.accounts.service;

import com.sanjeet.accounts.dto.CustomerDto;

import java.util.Optional;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchCustomerDetails(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
    Boolean updateCommunicationStatus(Long accountNumber);
}
