package com.ostdlabs;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repositories.BankAccountRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is used to initialize application with default data
 */

@Component
public class ApplicationInitializer implements SmartInitializingSingleton {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void afterSingletonsInstantiated() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("12232434");
        bankAccount.setBankName("Sberbank");
        bankAccount.setBic("DABAIE2D");
        bankAccount.setIban("AD1200012030200359100100");

        bankAccountRepository.save(bankAccount);
    }
}
