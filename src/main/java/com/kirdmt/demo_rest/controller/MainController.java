package com.kirdmt.demo_rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirdmt.demo_rest.entity.Operation;
import com.kirdmt.demo_rest.entity.Wallet;
import com.kirdmt.demo_rest.repository.WalletRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
public class MainController {
    //В БД 2 таблицы. 1. кошельки и количество средств. 2. операции по кошельку.
    //корректный тип данных для amount, не float?
    //необходимо реализовать корректные статусы ответов, а не 200 только.
    //operation id??
    // Стиль кода
    //переименование классов? Main...
    //выносить логику в service?
    //строковые константы


    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("api/v1/wallets/{walletId}")
    public Wallet getWalletBalance(@PathVariable Integer walletId){

        if (walletRepo.existsById(walletId)){
            Wallet wallet = walletRepo.findById(walletId).orElseThrow();
            return wallet;
        } else {
            log.info("wallet id is incorrect: " + walletId);
            return null;
        }
    }

    @PostMapping("api/v1/wallet")
    //public String makeOperation(@RequestParam Integer walletId, @RequestParam String operationType, @RequestParam Float amount){
    public Operation makeOperation(@RequestBody Operation operation){

        if(operation.getAmount() > 0) {

            if (operation.getOperationType().equals("DEPOSIT") | operation.getOperationType().equals("WITHDRAW")) {

                try{
                    Wallet wallet = walletRepo.findById(operation.getWalletId()).orElseThrow();

                    if (operation.getOperationType().equals("DEPOSIT")){

                        wallet.setAmount(wallet.getAmount() + operation.getAmount());
                        walletRepo.save(wallet);
                        operation.setOperationResult("SUCCESS");
                        return operation;

                    } else if (operation.getOperationType().equals("WITHDRAW") & wallet.getAmount() >= operation.getAmount()) {

                        wallet.setAmount(wallet.getAmount() - operation.getAmount());
                        walletRepo.save(wallet);
                        operation.setOperationResult("SUCCESS");
                        return operation;

                    } else operation.setOperationResult("FAILED. Not enough money.");

                } catch (NoSuchElementException elementException){
                    log.info("Exception is: " + elementException);
                    operation.setOperationResult("FAILED. Invalid walletId.");
                    return operation;
                } catch (Exception e){
                    log.info("Exception is: " + e);
                }

            } else {
                operation.setOperationResult("FAILED. Invalid operationType.");
                return operation;
            }
        } else {
            operation.setOperationResult("FAILED. Invalid amount.");
            return operation;
        }

        operation.setOperationResult("FAILED");
        return operation;
    }

    @GetMapping("api/v1/test")
    public String makeOperation(){
        return null;
    }

}




