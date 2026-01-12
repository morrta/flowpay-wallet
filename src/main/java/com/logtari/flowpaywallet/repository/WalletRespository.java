package com.logtari.flowpaywallet.repository;

import com.logtari.flowpaywallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRespository extends JpaRepository<Wallet, UUID> {
}
