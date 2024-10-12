package com.sanjeet.loans.service;

import com.sanjeet.loans.dto.LoanDto;

public interface ILoanService {
    void createLoan(String mobileNumber);
    LoanDto fechLoanDetails(String mobileNumber);
    boolean updateLoan(LoanDto loanDto);
    boolean removeLoan(String mobileNumber);
}
