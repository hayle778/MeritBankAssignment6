package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.dto.CDAccountDTO;
import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.*;
import com.meritamerica.assignment6.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountsServiceImpl implements BankAccountsService {

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private CDAccountRepository cdAccountRepository;

    @Autowired
    private CDOfferingRepository cdOfferingRepository;

    public BankAccountsServiceImpl() {}

    @Override
    public CheckingAccount addCheckingAccount(long id, CheckingAccount checkingAccount) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Checking Account failed to Post: Account Holder could not be located");
        }
        checkingAccount.setAccountHolder(accountHolder);
        return checkingAccountRepository.save(checkingAccount);
    }

    @Override
    public SavingsAccount addSavingsAccount(long id, SavingsAccount savingsAccount) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Savings Account failed to Post: Account Holder could not be located");
        }
        savingsAccount.setAccountHolder(accountHolder);
        return savingsAccountRepository.save(savingsAccount);
    }

    @Override
    public CDAccount addCDAccount(long id, CDAccountDTO cdAccountDTO) throws AccountHolderNotFoundException {
        CDOffering cdOffering = cdOfferingRepository.findById(cdAccountDTO.getCdOffering().getId());
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getCDOffering(), cdAccountDTO.getBalance());
        AccountHolder accountHolder = accountHoldersRepository.findById(id);

        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("CD Account failed to Post: Account Holder could not be located");
        }

        cdAccount.setAccountHolder(accountHolder);
        return cdAccountRepository.save(cdAccount);
    }
}
