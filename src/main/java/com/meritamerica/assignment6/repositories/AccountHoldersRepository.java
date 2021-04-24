package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.AccountHolder;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountHoldersRepository extends JpaRepository<AccountHolder, Long> {
}
