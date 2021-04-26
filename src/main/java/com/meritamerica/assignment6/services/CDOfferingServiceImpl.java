package com.meritamerica.assignment6.services;

import com.meritamerica.assignment6.exceptions.InvalidArgumentException;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.repositories.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDOfferingServiceImpl implements CDOfferingService {

    @Autowired
    private CDOfferingRepository cdOfferingRepository;



    @Override
    public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException {
        if (cdOffering.getInterestRate() <= 0 || cdOffering.getInterestRate() >= 1 || cdOffering.getTerm() < 1) {
            throw new InvalidArgumentException("Invalid Term or Interest Rate");
        }
        return cdOfferingRepository.save(cdOffering);
    }

    @Override
    public CDOffering getCDOffering(long id) {
        return cdOfferingRepository.findById(id);
    }

    @Override
    public List<CDOffering> getCDOfferings() {
        return cdOfferingRepository.findAll();
    }


}
