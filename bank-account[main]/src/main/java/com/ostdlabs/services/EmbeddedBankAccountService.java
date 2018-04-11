package com.ostdlabs.services;

import com.ostdlabs.exceptions.AccountNotFoundByIdException;
import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repositories.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Transactional
public class EmbeddedBankAccountService implements BankAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedBankAccountService.class);

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
        return bankAccountRepository.save(copyWithoutId(bankAccount));
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        Optional<BankAccount> existedAccount = bankAccountRepository.findById(bankAccount.getId());

        if (!existedAccount.isPresent()) {
            LOGGER.error("Account not found by id:{}", bankAccount.getId());
            throw new AccountNotFoundByIdException();
        }

        BankAccount target = existedAccount.get();
        copyProperties(bankAccount, target);
        return bankAccountRepository.save(target);

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

    private BankAccount copyWithoutId(BankAccount source) {
        BankAccount newAccount = new BankAccount();
        copyProperties(source, newAccount, "id");
        return newAccount;
    }
}
