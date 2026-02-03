package com.logtari.flowpaywallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.logtari.flowpaywallet.entity.Wallet;
import com.logtari.flowpaywallet.repository.WalletRespository;
import com.logtari.flowpaywallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest

public class WalletServiceTest {

    @Autowired
    WalletService underTest;
    @Autowired
    WalletRespository walletRespository;

    @Test
    void createWallet_should_persist_wallet_with_initial_balance() {
        UUID walletId = underTest.createWallet(BigDecimal.valueOf(100.00));
        Wallet wallet = walletRespository.findById(walletId).get();
        assertEquals(wallet.getId(), walletId);
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }

    @Test
    void deposit_should_increase_balance() {
        UUID walletId = underTest.createWallet(BigDecimal.valueOf(0));
        underTest.deposit(walletId, BigDecimal.valueOf(100.00));
        Wallet wallet = walletRespository.findById(walletId).get();
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }

    @Test
    void deposit_should_handle_precision_correctly() {
        UUID walletId = underTest.createWallet(BigDecimal.valueOf(0));
        underTest.deposit(walletId, BigDecimal.valueOf(0.1));
        underTest.deposit(walletId, BigDecimal.valueOf(0.2));

        Wallet wallet = walletRespository.findById(walletId).get();
        assertEquals(new BigDecimal("0.30"),wallet.getBalance());
    }
    @Test
    void deposit_should_not_partially_apply_on_failure(){
        UUID walletId = underTest.createWallet(BigDecimal.ZERO);
        assertThrows(RuntimeException.class, ()->underTest.depositWithFailure(walletId, BigDecimal.TEN));
        Wallet wallet = walletRespository.findById(walletId).get();
        assertEquals(new BigDecimal("0.00"),wallet.getBalance());
    }

    @Test
    void deposit_should_reject_negative_amount(){
        UUID walletId = underTest.createWallet(new BigDecimal("100.00"));
        assertThrows(IllegalArgumentException.class, ()->underTest.deposit(walletId, new BigDecimal("-50.00")));
    }
}
