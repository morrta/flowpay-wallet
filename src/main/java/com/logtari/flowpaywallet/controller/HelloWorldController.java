package com.logtari.flowpaywallet.controller;

import com.logtari.flowpaywallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallets/")
@RequiredArgsConstructor
public class HelloWorldController {

    WalletService walletService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello Mohamed Logtari!";
    }
    @PostMapping("/{walletId}/deposit")
    public void deposit(@PathVariable UUID walletId, @RequestParam double amount){
        walletService.deposit(walletId,amount);
    }
}
