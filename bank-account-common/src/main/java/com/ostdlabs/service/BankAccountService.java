package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount get(Long id);

    List<BankAccount> findAll();

    BankAccount add(BankAccount bankAccount);

    boolean delete(Long id);

}
