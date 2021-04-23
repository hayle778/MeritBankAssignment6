package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolder, Long> {
}
