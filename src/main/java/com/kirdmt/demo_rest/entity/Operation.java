package com.kirdmt.demo_rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "operations")
public class Operation {

    @Id
    //@Column(unique = true, nullable = false)
    private int walletId;

    //@Column(nullable = false)
    private String operationType;

    //@Column(nullable = false)
    private int amount;

    //@Column(nullable = false)
    private String operationResult;

}
