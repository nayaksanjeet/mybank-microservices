package com.sanjeet.accounts.mapper;


import com.sanjeet.accounts.dto.AccountsDto;
import com.sanjeet.accounts.entity.Accounts;
import lombok.Data;

@Data
public class AccountsMapper {

    public static  AccountsDto mapAccountsToDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        return accountsDto;
    }
    public static Accounts mapAccountDtoToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        return accounts;
    }
}
