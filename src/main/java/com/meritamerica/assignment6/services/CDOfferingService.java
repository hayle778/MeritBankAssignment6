package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.exceptions.InvalidArgumentException;
import com.meritamerica.assignment6.models.CDOffering;

import java.util.List;

public interface CDOfferingService {

    public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException;

    public CDOffering getCDOffering(long id);

    public List<CDOffering> getCDOfferings();
}
