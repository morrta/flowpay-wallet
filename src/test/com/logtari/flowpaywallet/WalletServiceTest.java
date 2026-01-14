package com.logtari.flowpaywallet;

import com.logtari.flowpaywallet.entity.Wallet;
import com.logtari.flowpaywallet.repository.WalletRespository;
import com.logtari.flowpaywallet.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class WalletServiceTest {

    @Autowired
    WalletService underTest;
    @Autowired
    WalletRespository walletRespository;

    @Test
    void createWallet_should_persist_wallet_with_initial_balance() {
        UUID walletId = underTest.createWallet(100.0);
        Wallet wallet = walletRespository.findById(walletId).get();
        assertEquals(wallet.getId(), walletId);
        assertEquals(100.0, wallet.getBalance());
    }
}
