package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> findAll();

    BankAccount add(BankAccount bankAccount);
}
