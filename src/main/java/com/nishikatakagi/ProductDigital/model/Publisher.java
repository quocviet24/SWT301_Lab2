package com.nishikatakagi.ProductDigital.model;

import java.util.Date;

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
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String image;
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
}