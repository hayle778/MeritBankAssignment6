package com.meritamerica.assignment6.controllers;

import com.meritamerica.assignment6.dto.CDAccountDTO;
import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.exceptions.OfferingNotFoundException;
import com.meritamerica.assignment6.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * This method takes in a checking account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the checking
     * account.
     *
     * @param id the path variable to an individual account holder.
     * @param checkingAccount the checking account to be posted to the API.
     * @return the checking account posted to the API.
     */
    @PostMapping(value="/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingAccount postCheckingAccountById(@PathVariable("id") int id, @RequestBody @Valid CheckingAccount checkingAccount)
            throws ExceedsFraudSuspicionLimitException, ExceedsCombinedBalanceLimitException, AccountHolderNotFoundException {

        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Checking Account failed to Post: Account Holder could not be located");
        }
        accountHolder.addCheckingAccount(checkingAccount);
        return checkingAccount;
    }

    /**
     * This method retrieves an array of all the checking accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders checking accounts.
     */
    @GetMapping(value="AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountsById(@PathVariable("id") int id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Checking Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getCheckingAccounts();
    }

    /**
     * This method takes in a savings account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the savings
     * account.
     *
     * @param id the path variable to an individual account holder.
     * @param savingsAccount the savings account to be posted to the API.
     * @return the savings account posted to the API.
     */
    @PostMapping(value="AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount postSavingsAccountById(@PathVariable("id") int id, @RequestBody @Valid SavingsAccount savingsAccount)
            throws ExceedsFraudSuspicionLimitException, ExceedsCombinedBalanceLimitException, AccountHolderNotFoundException {

        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Savings Account failed to Post: Account Holder could not be located");
        }
        accountHolder.addSavingsAccount(savingsAccount);
        return savingsAccount;
    }

    /**
     * This method retrieves an array of all the savings accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders savings accounts.
     */
    @GetMapping(value="AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingsAccount> getSavingsAccountsById(@PathVariable("id") int id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("Savings Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getSavingsAccounts();
    }
    /**
     * This method takes in a cd account of an individual account holder designated
     * by the path variable and post it to the API of Merit Bank and returns the cd
     * account.
     *
     * @param id the path variable to an individual account holder.
     * @param cdAccountDTO the cd account to be posted to the API.
     * @return the cd account posted to the API.
     */
    @PostMapping(value="AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CDAccount postCDAccountById(@PathVariable("id") int id, @RequestBody CDAccountDTO cdAccountDTO)
            throws ExceedsFraudSuspicionLimitException, AccountHolderNotFoundException, OfferingNotFoundException {

        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("CD Account failed to Post: Account Holder could not be located");
        }
        System.out.println(cdAccountDTO.getCDOffering());
        if (cdAccountDTO.getCDOffering().getId() == 0) {
            throw new OfferingNotFoundException("The CD Offering could not be located");
        }
        CDOffering cdOffering = MeritBank.getCDOfferingById(cdAccountDTO.getCDOffering().getId());
        CDAccount cdAccount = new CDAccount(cdOffering, cdAccountDTO.getBalance());

        accountHolder.addCDAccount(cdAccount);
        return cdAccount;
    }

    /**
     * This method retrieves an array of all the cd accounts of an individual account
     * holder of Merit Bank.
     *
     * @param id the path variable to an individual account holder
     * @return an array of the requested account holders cd accounts.
     */
    @GetMapping(value="AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CDAccount> getCDAccountsById(@PathVariable("id") int id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null) {
            throw new AccountHolderNotFoundException("CD Account cannot be located: Account Holder could not be located");
        }
        return accountHolder.getCDAccounts();
    }
}
