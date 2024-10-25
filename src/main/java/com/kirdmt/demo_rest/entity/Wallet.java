package com.kirdmt.demo_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {
    //
    // @Column(unique = true, nullable = false)

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    public Wallet(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Wallet() {
    }

}
