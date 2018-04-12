package com.ostdlabs.web.controllers;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.services.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<BankAccount> findAll() {
        return bankAccountService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BankAccount add(@RequestBody BankAccount bankAccount) {
        LOGGER.info("Adding new bank account");
        return bankAccountService.add(bankAccount);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public BankAccount edit(@RequestBody BankAccount bankAccount) {
        LOGGER.info("Edit bank account. Id: {}", bankAccount.getId());
        return bankAccountService.update(bankAccount);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public boolean delete(@PathVariable Long id) {
        LOGGER.info("Deleting bank account. Id: {}", id);
        return bankAccountService.delete(id);
    }



}
