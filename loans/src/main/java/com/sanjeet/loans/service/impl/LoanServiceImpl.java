package com.sanjeet.loans.service.impl;

import com.sanjeet.loans.constants.LoansConstants;
import com.sanjeet.loans.dto.LoanDto;
import com.sanjeet.loans.entity.Loans;
import com.sanjeet.loans.exception.LoanAlreadyExistsException;
import com.sanjeet.loans.repository.LoanRepository;
import com.sanjeet.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loanRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists for this mobile number "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setMobileNumber(mobileNumber);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }
}
