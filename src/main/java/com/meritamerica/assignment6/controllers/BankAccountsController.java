package com.meritamerica.assignment6.controllers;

import com.meritamerica.assignment6.dto.CDAccountDTO;
import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.exceptions.OfferingNotFoundException;
import com.meritamerica.assignment6.models.*;
import com.meritamerica.assignment6.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller class allows bank accounts associated with account holders to
 * be posted and retrieved from Merit Banks API.
 */
@RestController
public class BankAccountsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    /** the database of checking accounts */
    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    /** the database of savings accounts */
    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    /** the database of cd accounts */
    @Autowired
    CDAccountRepository cdAccountRepository;

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    //region Checking Accounts
    /**
     * this method takes in a checking account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the checking
     * account.
     *
     * @param id the path variable to an individual account holder
     * @param checkingAccount the checking account to be posted to the API
     * @return the checking account posted to the API
     */
    // TODO Need to figure out how to link the account holder to the account
    @PostMapping(value="/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingAccount postCheckingAccountById(@PathVariable("id") long id, @RequestBody @Valid CheckingAccount checkingAccount)
            throws ExceedsFraudSuspicionLimitException, ExceedsCombinedBalanceLimitException, AccountHolderNotFoundException {

        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Checking Account failed to Post: Account Holder could not be located");
        }
        checkingAccount.setAccountHolder(accountHolder);
        return checkingAccountRepository.save(checkingAccount);
    }

    /**
     * this method retrieves an array of all the checking accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders checking accounts
     */
    @GetMapping(value="AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountsById(@PathVariable("id") long id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Checking Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getCheckingAccounts();
    }

    //endregion

    //region Savings Accounts
    /**
     * this method takes in a savings account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the savings
     * account.
     *
     * @param id the path variable to an individual account holder
     * @param savingsAccount the savings account to be posted to the API
     * @return the savings account posted to the API
     */
    @PostMapping(value="AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount postSavingsAccountById(@PathVariable("id") long id, @RequestBody @Valid SavingsAccount savingsAccount)
            throws ExceedsFraudSuspicionLimitException, ExceedsCombinedBalanceLimitException, AccountHolderNotFoundException {

        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Savings Account failed to Post: Account Holder could not be located");
        }
        savingsAccount.setAccountHolder(accountHolder);
        return savingsAccountRepository.save(savingsAccount);
    }

    /**
     * this method retrieves an array of all the savings accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders savings accounts
     */
    @GetMapping(value="AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingsAccount> getSavingsAccountsById(@PathVariable("id") long id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = accountHoldersRepository.findById(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Savings Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getSavingsAccounts();
    }
    //endregion

    //region CD Accounts
    /**
     * this method takes in a cd account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the cd
     * account.
     *
     * @param id the path variable to an individual account holder
     * @param cdAccountDTO the cd account to be posted to the API
     * @return the cd account posted to the API
     */
    @PostMapping(value="AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CDAccount postCDAccountById(@PathVariable("id") long id, @RequestBody CDAccountDTO cdAccountDTO)
            throws ExceedsFraudSuspicionLimitException, AccountHolderNotFoundException, OfferingNotFoundException {

        CDOffering cdOffering = cdOfferingRepository.findById(cdAccountDTO.getCdOffering().getId());
        CDAccount cdAccount = new CDAccount(cdAccountDTO.getCDOffering(), cdAccountDTO.getBalance());
        AccountHolder accountHolder = accountHoldersRepository.findById(id);

        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("CD Account failed to Post: Account Holder could not be located");
        }

        cdAccount.setAccountHolder(accountHolder);
        return cdAccountRepository.save(cdAccount);
    }

    /**
     * this method retrieves an array of all the cd accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders cd accounts
     */
    @GetMapping(value="AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CDAccount> getCDAccountsById(@PathVariable("id") long id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("CD Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getCDAccounts();
    }
    //endregion
}
