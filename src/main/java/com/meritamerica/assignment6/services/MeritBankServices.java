//package com.meritamerica.assignment6.services;
//
//import com.meritamerica.assignment6.models.AccountHolder;
//import com.meritamerica.assignment6.models.AccountHolderContactDetails;
//import com.meritamerica.assignment6.repositories.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class MeritBankServices {
//
//    @Autowired
//    private AccountHoldersRepository accountHoldersRepository;
//
//    @Autowired
//    private AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;
//
//    @Autowired
//    private CheckingAccountRepository checkingAccountRepository;
//
//    @Autowired
//    private SavingsAccountRepository savingsAccountRepository;
//
//    @Autowired
//    private CDAccountRepository cdAccountRepository;
//
//    @Autowired
//    private CDOfferingRepository cdOfferingRepository;
//
//    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
//        return accountHoldersRepository.save(accountHolder);
//    }
//
//    public List<AccountHolder> getAccountHolders() {
//        return accountHoldersRepository.findAll();
//    }
//
//    public AccountHolder getAccountHolderById(long id) {
//        return accountHoldersRepository.findById(id).orElse(null);
//    }
//
//    public AccountHolderContactDetails addContactDetails()
//
//
//}
