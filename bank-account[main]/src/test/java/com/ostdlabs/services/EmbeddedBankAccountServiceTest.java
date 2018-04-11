package com.ostdlabs.services;

import com.ostdlabs.exceptions.AccountNotFoundByIdException;
import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repositories.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    public void shouldUpdate_WhenAccountFoundById() throws Exception {
        //arrange
        BankAccount bankAccount_1 = new BankAccount();
        bankAccount_1.setId(1L);
        bankAccount_1.setAccountNumber("num_1");

        BankAccount bankAccount_2 = new BankAccount();
        bankAccount_2.setId(1L);
        bankAccount_2.setAccountNumber("num_2");

        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(bankAccount_1));
        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //act
        BankAccount result = accountService.update(bankAccount_2);

        //assert
        assertThat(result, hasProperty("accountNumber", is("num_2")));

    }

    @Test(expected = AccountNotFoundByIdException.class)
    public void shouldThrowException_WhenUpdateAndAccountNotFoundById() throws Exception {
        //arrange
        BankAccount bankAccount_1 = new BankAccount();
        bankAccount_1.setId(1L);
        bankAccount_1.setAccountNumber("num_1");

        BankAccount bankAccount_2 = new BankAccount();
        bankAccount_2.setId(2L);
        bankAccount_2.setAccountNumber("num_2");

        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act
        accountService.update(bankAccount_2);

    }
}