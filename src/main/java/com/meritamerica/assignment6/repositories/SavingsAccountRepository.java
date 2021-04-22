package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
