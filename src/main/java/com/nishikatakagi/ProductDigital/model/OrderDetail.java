package com.nishikatakagi.ProductDigital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_details")
public class OrderDetail {
    public OrderDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @ManyToOne
    @JoinColumn(name = "card_id")
    Card card;

    Integer quantity;
    @Column(columnDefinition = "decimal(10,2)")
    Double total;
}
