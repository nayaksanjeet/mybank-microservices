package com.sanjeet.loans.service.impl;

import com.sanjeet.loans.constants.LoansConstants;
import com.sanjeet.loans.dto.LoanDto;
import com.sanjeet.loans.entity.Loans;
import com.sanjeet.loans.exception.LoanAlreadyExistsException;
import com.sanjeet.loans.exception.ResouceNotFoundException;
import com.sanjeet.loans.mapper.LoansMapper;
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

    @Override
    public LoanDto fechLoanDetails(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResouceNotFoundException("Loan not found for this mobile number "+mobileNumber));
        return LoansMapper.mapLoansToDto(loans, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loans loans = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(() -> new ResouceNotFoundException("Loan not found for this loanNumber "+loanDto.getLoanNumber()));
        loanDto.setOutstandingAmount(loanDto.getTotalLoan()-loanDto.getAmountPaid());
        LoansMapper.mapDtoToLoans(loanDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean removeLoan(String mobileNumber) {
        Loans loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResouceNotFoundException("Loan not found for this mobile number "+mobileNumber));
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }
}
