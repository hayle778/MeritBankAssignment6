package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.dto.CDAccountDTO;
import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.SavingsAccount;

public interface BankAccountsService {

public CheckingAccount addCheckingAccount(long id, CheckingAccount checkingAccount) throws AccountHolderNotFoundException;

public SavingsAccount addSavingsAccount(long id, SavingsAccount savingsAccount) throws AccountHolderNotFoundException;

public CDAccount addCDAccount(long id, CDAccountDTO cdAccountDTO) throws AccountHolderNotFoundException;
}
