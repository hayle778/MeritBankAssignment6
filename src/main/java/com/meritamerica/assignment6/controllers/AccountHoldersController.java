package com.meritamerica.assignment6.controllers;

import com.meritamerica.assignment6.exceptions.AccountHolderNotFoundException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.AccountHolderContactDetails;
import com.meritamerica.assignment6.repositories.AccountHoldersContactDetailsRepository;
import com.meritamerica.assignment6.repositories.AccountHoldersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * This controller class allows account holders to be posted and retrieved from
 * Merit Banks API.
 */
@RestController
public class AccountHoldersController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /** the database of account holders */
    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    /** the database of the contact information of account holders */
    @Autowired
    AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;



    /**
     * this method takes in a account holder and post it to the API of Merit Bank and
     * returns the account holder.
     *
     * @param accountHolder the account holder to be posted to the API
     * @return the account holder posted to the API
     */
    @PostMapping(value="/AccountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder postAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
        return accountHoldersRepository.save(accountHolder);
    }

    /**
     * this method retrieves an array of all of Merit Banks account holders.
     *
     * @return an array of Merit Banks account holders
     */
    @GetMapping(value = "/AccountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders() throws AccountHolderNotFoundException {
        return accountHoldersRepository.findAll();
    }

    /**
     * this method retrieves a single account holder from a given id from Merit Bank.
     *
     * @param id the path variable to an individual account holder of Merit Bank
     * @return the requested account holder of Merit Bank
     */
    @GetMapping(value = "/AccountHolders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable("id") long id) throws AccountHolderNotFoundException {
        return accountHoldersRepository.findById(id).orElse(null);
    }


    /**
     * this method retrieves an array of all of Merit Banks account holders contact
     * details.
     *
     * @param id the path variable to an individual account holders contact details
     * @param contactDetails the contact details of an account holder
     * @return an array of Merit Banks account holders contact details
     */
    @PostMapping(value = "/AccountHoldersContactDetails")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderContactDetails postContactDetails
            (long id, @RequestBody @Valid AccountHolderContactDetails contactDetails) {
//       AccountHolder accountHolder = accountHoldersRepository.findById(id).orElse(null);
       return accountHoldersContactDetailsRepository.save(contactDetails);
    }

    /**
     * this method retrieves an array of all the contact details for all of Merit
     * Banks account holders
     *
     * @return an array of Merit Banks account holders contact details
     */
    @GetMapping(value = "/AccountHoldersContactDetails")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolderContactDetails> getContactDetails() {
        return accountHoldersContactDetailsRepository.findAll();
    }

    /**
     * this method retrieves a single account holders contact details from a given id
     * from Merit Bank.
     *
     * @param id the path variable to an individual account holders contact details
     * @return the requested account holders contact details
     */
    @GetMapping(value = "/AccountHoldersContactDetails/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolderContactDetails getContactDetailsById(@PathVariable("id") long id) {
        return accountHoldersContactDetailsRepository.findById(id).orElse(null);
    }
}
