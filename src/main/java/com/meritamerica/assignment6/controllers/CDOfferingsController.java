package com.meritamerica.assignment6.controllers;

import com.meritamerica.assignment6.exceptions.*;
import com.meritamerica.assignment6.models.*;
import com.meritamerica.assignment6.repositories.CDOfferingRepository;
import com.meritamerica.assignment6.services.CDOfferingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * This controller class allows cd offerings of Merit Bank to posted and retrieved from
 * Merit Banks API.
 */
@RestController
public class CDOfferingsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /** the database of cd offerings */
    @Autowired
    CDOfferingService cdOfferingService;

    /**
     * this method takes in a cd offering and post it to the API of Merit Bank and
     * returns the cd offering.
     *
     * @param cdOffering the cd offering to be posted to the API
     * @return the cd offering posted to the API
     */
    @PostMapping(value="/CDOfferings")
    @ResponseStatus(HttpStatus.CREATED)
    public CDOffering postCDOffering(@RequestBody CDOffering cdOffering) throws InvalidArgumentException {
        return cdOfferingService.addCDOffering(cdOffering);
    }

    /**
     * this method retrieves an array of all the cd offerings available from Merit Bank.
     *
     * @return an array of Merit Banks cd offerings
     */
    @GetMapping(value="/CDOfferings")
    @ResponseStatus(HttpStatus.OK)
    public List<CDOffering> getCDOfferings() {
        return cdOfferingService.getCDOfferings();
    }

    /**
     * this method retrieves a single cd offering from a given id from Merit Bank.
     *
     * @param id the path variable to an individual cd offering
     * @return the requested cd offering from Merit Bank
     */
    @GetMapping(value="CDOfferings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CDOffering getCDOfferingById(@PathVariable("id") long id) {
        return cdOfferingService.getCDOffering(id);
    }
}
