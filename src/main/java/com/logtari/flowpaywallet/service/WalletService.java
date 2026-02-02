package com.logtari.flowpaywallet.service;

import com.logtari.flowpaywallet.common.WalletNotFoundException;
import com.logtari.flowpaywallet.entity.Wallet;
import com.logtari.flowpaywallet.repository.WalletRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRespository walletRespository;

    public UUID createWallet(BigDecimal initialBalance){
        Wallet wallet = Wallet.builder()
                .id(UUID.randomUUID())
                .balance(initialBalance)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        walletRespository.save(wallet);
        return wallet.getId();
    }
    public void deposit(UUID walletId, BigDecimal amount){
        Wallet wallet = walletRespository.findById(walletId).orElseThrow(()->new WalletNotFoundException("No Wallet found with id: %s ".formatted(walletId)));
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRespository.save(wallet);
    }
}
