package com.ostdlabs.repository;

import com.ostdlabs.model.BankAccount;

import java.util.List;

public interface BankAccountRepository {
    List<BankAccount> findAll();
}
