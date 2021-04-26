package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountHoldersService {

    public AccountHolder addAccountHolder(AccountHolder accountHolder);

    public AccountHolder getAccountHolder(long id);

    public List<AccountHolder> getAccountHolders();

    public AccountHolderContactDetails addContactDetails(long id, AccountHolderContactDetails contactDetails);

    public List<CheckingAccount> getCheckingAccounts(long id) throws AccountHolderNotFoundException;

    public List<SavingsAccount> getSavingsAccounts(long id) throws AccountHolderNotFoundException;

    public List<CDAccount> getCDAccounts(long id) throws AccountHolderNotFoundException;
}
