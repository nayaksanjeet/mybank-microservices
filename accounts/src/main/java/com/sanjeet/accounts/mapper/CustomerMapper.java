package com.sanjeet.accounts.mapper;

import com.sanjeet.accounts.dto.CustomerDetailsDto;
import com.sanjeet.accounts.dto.CustomerDto;
import com.sanjeet.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapCustomerToDto(Customer customer, CustomerDto customerDto) {

        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;

    }
    public static Customer mapDtoToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDetailsDto mapCustomerToDto(Customer customer, CustomerDetailsDto customerDetailsDto) {

        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;

    }
    public static Customer mapDtoToCustomer(CustomerDetailsDto customerDetailsDto, Customer customer) {
        customer.setName(customerDetailsDto.getName());
        customer.setEmail(customerDetailsDto.getEmail());
        customer.setMobileNumber(customerDetailsDto.getMobileNumber());
        return customer;
    }


}
