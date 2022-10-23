package com.codegym.cms.service;

import com.codegym.cms.model.Spending;
import com.codegym.cms.repository.ISpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpendingService implements ISpendingService {
    @Autowired
    private ISpendingRepository spendingRepository;

    @Override
    public List<Spending> findAll() {
        return spendingRepository.findAll();
    }

    @Override
    public Spending findById(Long id) {
        return spendingRepository.findById(id);
    }

    @Override
    public void save(Spending spending) {
        spendingRepository.save(spending);
    }

    @Override
    public void remove(Long id) {
        spendingRepository.remove(id);
    }
}