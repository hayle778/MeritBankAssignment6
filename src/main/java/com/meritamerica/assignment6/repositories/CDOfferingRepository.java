package com.meritamerica.assignment6.repositories;

import com.meritamerica.assignment6.models.CDOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDOfferingRepository extends JpaRepository<CDOffering, Long> {
    CDOffering findById(long id);
}
