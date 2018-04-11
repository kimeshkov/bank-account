package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repository.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmbeddedBankAccountService implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public EmbeddedBankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount get(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> result = new ArrayList<>();
        bankAccountRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public BankAccount add(BankAccount bankAccount) {
        return bankAccountRepository.save(copyFrom(bankAccount));
    }

    @Override
    public boolean delete(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()) {
            bankAccountRepository.delete(bankAccount.get());
            return true;
        }
        return false;
    }

    private BankAccount copyFrom(BankAccount source) {
        BankAccount newAccount = new BankAccount();
        BeanUtils.copyProperties(source, newAccount, "id");
        return newAccount;
    }
}
