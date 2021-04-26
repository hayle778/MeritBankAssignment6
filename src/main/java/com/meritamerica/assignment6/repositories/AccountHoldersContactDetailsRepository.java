package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.AccountHolderContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHoldersContactDetailsRepository
        extends JpaRepository<AccountHolderContactDetails, Long> {
    AccountHolderContactDetails findById(long id);
}
