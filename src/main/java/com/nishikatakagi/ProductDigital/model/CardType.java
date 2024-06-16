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
@Data
@Table(name = "card_types")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardType {

    public CardType() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisher;
    @ManyToOne
    @JoinColumn(name = "money_id")
    Money money;
    int inStock;
    int soldQuantity;
    Boolean isDeleted;
    @Column(columnDefinition = "Date")
    Date deletedDate;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    User deletedBy;
    @Column(columnDefinition = "DATE")
    Date createdDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

    @Override
    public String toString() {
        return "Id " + id + " PublisherId " + publisher.getId();
    }
}
