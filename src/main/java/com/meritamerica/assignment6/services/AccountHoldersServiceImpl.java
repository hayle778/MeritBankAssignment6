package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.*;
import com.meritamerica.assignment6.repositories.AccountHoldersContactDetailsRepository;
import com.meritamerica.assignment6.repositories.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHoldersServiceImpl implements AccountHoldersService{

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    @Autowired
    private AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHoldersRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolder(long id) {
        return accountHoldersRepository.findById(id);
    }

    @Override
    public List<AccountHolder> getAccountHolders() {
        return accountHoldersRepository.findAll();
    }

    @Override
    public AccountHolderContactDetails addContactDetails(long id, AccountHolderContactDetails contactDetails) {
        AccountHolder accountHolder = accountHoldersRepository.findById(id);

        contactDetails.setAccountHolder(accountHolder);
        return accountHoldersContactDetailsRepository.save(contactDetails);
    }

    @Override
    public List<CheckingAccount> getCheckingAccounts(long id) throws AccountHolderNotFoundException{
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException();
        }
        return accountHolder.getCheckingAccounts();
    }

    @Override
    public List<SavingsAccount> getSavingsAccounts(long id) throws AccountHolderNotFoundException{
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException();
        }
        return accountHolder.getSavingsAccounts();
    }

    @Override
    public List<CDAccount> getCDAccounts(long id) throws AccountHolderNotFoundException{
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException();
        }
        return accountHolder.getCDAccounts();
    }
}
