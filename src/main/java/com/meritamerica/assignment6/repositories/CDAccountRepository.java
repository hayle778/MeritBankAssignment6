package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.CDAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDAccountRepository extends JpaRepository<CDAccount, Long> {
}
