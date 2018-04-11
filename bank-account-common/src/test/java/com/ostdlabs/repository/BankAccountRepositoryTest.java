package com.ostdlabs.repository;

import com.ostdlabs.model.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void shouldGenerateId_WhenSaveWithoutId() throws Exception {
        //arrange
        BankAccount newAccount = new BankAccount();
        newAccount.setAccountNumber("accountNumber");

        //act
        BankAccount result = bankAccountRepository.save(newAccount);

        //assert
        assertThat(result, hasProperty("id", notNullValue()));

    }

    @Test
    public void shouldCreateNewEntity_WhenSaveWithoutIdAndOneEntityAlreadyExist() throws Exception {
        //arrange
        BankAccount newAccount = new BankAccount();
        newAccount.setAccountNumber("accountNumber");

        bankAccountRepository.save(newAccount);

        BankAccount newAccount_2 = new BankAccount();
        newAccount_2.setAccountNumber("accountNumber_2");

        //act
        bankAccountRepository.save(newAccount_2);

        //assert
        Iterable<BankAccount> result = bankAccountRepository.findAll();

        assertThat(result,
                containsInAnyOrder(
                        hasProperty("accountNumber", is("accountNumber")),
                        hasProperty("accountNumber", is("accountNumber_2"))
                )
        );

    }

    @Test
    public void shouldUpdateOldEntity_WhenSaveSameObject() throws Exception {
        //arrange
        BankAccount newAccount = new BankAccount();
        newAccount.setAccountNumber("accountNumber");

        BankAccount savedAccount  = bankAccountRepository.save(newAccount);


        savedAccount.setAccountNumber("accountNumber_2");

        //act
        bankAccountRepository.save(savedAccount);

        //assert
        Iterable<BankAccount> result = bankAccountRepository.findAll();


        assertThat(result,
                contains(
                        hasProperty("accountNumber", is("accountNumber_2"))
                )
        );

    }
}