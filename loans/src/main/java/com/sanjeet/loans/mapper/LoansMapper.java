package com.sanjeet.loans.mapper;

import com.sanjeet.loans.dto.LoanDto;
import com.sanjeet.loans.entity.Loans;

public class LoansMapper {

    public static Loans mapDtoToLoans(LoanDto loanDto,Loans loans) {
       // Loans loans = new Loans();
        loans.setMobileNumber(loanDto.getMobileNumber());
        loans.setLoanNumber(loanDto.getLoanNumber());
        loans.setLoanType(loanDto.getLoanType());
        loans.setTotalLoan(loanDto.getTotalLoan());
        loans.setAmountPaid(loanDto.getAmountPaid());
        loans.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loans;
    }
    public static LoanDto mapLoansToDto(Loans loans,LoanDto loanDto) {
        //LoanDto loanDto = new LoanDto();
        loanDto.setMobileNumber(loans.getMobileNumber());
        loanDto.setLoanNumber(loans.getLoanNumber());
        loanDto.setLoanType(loans.getLoanType());
        loanDto.setTotalLoan(loans.getTotalLoan());
        loanDto.setAmountPaid(loans.getAmountPaid());
        loanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDto;
    }
}
