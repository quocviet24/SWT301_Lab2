package com.nishikatakagi.ProductDigital.model;

import java.sql.Date;

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
@Table(name = "cards")
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Card {
    public Card() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
	@JoinColumn(name = "card_type_id")
    CardType cardType;
    String seriNumber;
    String cardNumber;
    @Column(columnDefinition = "Date")
    Date expiryDate;
    Boolean isDeleted;
    @Column(columnDefinition = "Date")
    Date deletedDate;
    @ManyToOne
	@JoinColumn(name = "deleted_by")
    User deletedBy;
    @Column(columnDefinition = "Date")
    Date createdDate;
    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;
    @Column(columnDefinition = "Date")
    Date lastUpdated;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    User updatedBy;

    @Override
    public String toString() {
        return "id " + id + " cardType " + cardType.getId() + " seriNumber " + seriNumber + " cardNumber " + cardNumber + " expiryDate " + expiryDate;
    }
}
