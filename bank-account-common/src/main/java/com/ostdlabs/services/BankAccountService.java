package com.ostdlabs.services;

import com.ostdlabs.model.BankAccount;

import java.util.List;

/**
 * This service provides basic operations with bank accounts
 */
public interface BankAccountService {

    /**
     * Get bank account by id.
     * @param id bank account id
     * @return found bank account or null in case if no account exists
     * @see BankAccount
     */
    BankAccount get(Long id);

    /**
     * Find all existing bank accounts
     * @return all bank accounts
     * @see BankAccount
     */
    List<BankAccount> findAll();

    /**
     * Add new bank account
     * @param bankAccount to add
     * @return created bank account
     * @see BankAccount
     */
    BankAccount add(BankAccount bankAccount);

    /**
     * Update existed bank account
     * @param bankAccount for update
     * @return updated bank account
     * @see BankAccount
     */
    BankAccount update(BankAccount bankAccount);

    /**
     * Delete bank account by id
     * @param id bank account id
     * @return true if account was deleted
     */
    boolean delete(Long id);

}
