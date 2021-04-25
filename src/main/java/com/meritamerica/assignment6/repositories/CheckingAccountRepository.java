package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
    List<CheckingAccount> findByAccountHolder(long id);
}
