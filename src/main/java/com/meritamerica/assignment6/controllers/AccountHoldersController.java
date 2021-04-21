package com.meritamerica.assignment6.controllers;

import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.MeritBank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller class allows account holders to be posted and retrieved from
 * Merit Banks API.
 */
@RestController
public class AccountHoldersController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This method takes in a account holder and post it to the API of Merit Bank and
     * returns the account holder.
     *
     * @param accountHolder the account holder to be posted to the API.
     * @return the account holder posted to the API.
     */
    @PostMapping(value="/AccountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder postAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
        MeritBank.addAccountHolder(accountHolder);
        return accountHolder;
    }

    /**
     * This method retrieves an array of all of Merit Banks account holders.
     *
     * @return an array of Merit Banks account holders.
     */
    @GetMapping(value="/AccountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders() throws AccountHolderNotFoundException {
        if (MeritBank.getAccountHolders().size() < 1) {
            throw new AccountHolderNotFoundException("No Account Holders exist");
        }
        return MeritBank.getAccountHolders();
    }

    /**
     * This method retrieves a single account holder from a given id from Merit Bank.
     *
     * @param id the path variable to an individual account holder of Merit Bank.
     * @return the requested account holder of Merit Bank.
     */
    @GetMapping(value="/AccountHolders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable("id") int id) throws AccountHolderNotFoundException {
        AccountHolder accountHolder = MeritBank.getAccountHolderbyId(id);
        if (accountHolder == null)  {
            throw new AccountHolderNotFoundException("Account not found");
        }
        return accountHolder;
    }
}
