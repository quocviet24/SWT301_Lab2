package com.nishikatakagi.ProductDigital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "money")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(columnDefinition = "decimal(10,2)")
    double unitPrice;
    @Column(columnDefinition = "decimal(10,2)")
    double unitFund;

    public Money() {

    }
}
