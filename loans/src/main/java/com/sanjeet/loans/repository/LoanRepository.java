package com.sanjeet.loans.repository;

import com.sanjeet.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Integer> {

    Optional<Loans> findByMobileNumber(String loanNumber);
}
