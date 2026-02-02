package com.logtari.flowpaywallet.controller;

import com.logtari.flowpaywallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/wallets/")
@RequiredArgsConstructor
public class WalletController {

    WalletService walletService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello Mohamed Logtari!";
    }
    @PostMapping
    public UUID createWallet(@RequestParam BigDecimal initialBalance){
        return walletService.createWallet(initialBalance);
    }
    @PostMapping("/{walletId}/deposit")
    public void deposit(@PathVariable UUID walletId, @RequestParam BigDecimal amount){
        walletService.deposit(walletId,amount);
    }

}
