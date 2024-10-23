package com.sanjeet.accounts.service;

import com.sanjeet.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String correlationId,String mobileNumber);
}
