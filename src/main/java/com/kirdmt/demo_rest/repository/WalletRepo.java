package com.kirdmt.demo_rest.repository;

import com.kirdmt.demo_rest.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {

}
