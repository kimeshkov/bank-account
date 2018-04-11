package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repository.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmbeddedBankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private EmbeddedBankAccountService accountService;


    @Test
    public void shouldFindAllBankAccounts() throws Exception {
        //arrange
        BankAccount bankAccount_1 = new BankAccount();
        bankAccount_1.setAccountNumber("num_1");

        BankAccount bankAccount_2 = new BankAccount();
        bankAccount_2.setAccountNumber("num_2");


        when(bankAccountRepository.findAll()).thenReturn(Arrays.asList(bankAccount_1, bankAccount_2));

        //act
        List<BankAccount> result = accountService.findAll();

        //assert
        assertThat(result, containsInAnyOrder(
                hasProperty("accountNumber", is("num_1")),
                hasProperty("accountNumber", is("num_2"))
        ));

    }
}