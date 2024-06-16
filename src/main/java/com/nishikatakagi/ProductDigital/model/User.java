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
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Users")
public class User {
	public User() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 int id;
	 String username;
	 String password;
	 String email;
	 @Column(columnDefinition = "NCHAR(10)")
	 String phone;
	 String firstName;
	 String lastName;
	 int roleId;
	 boolean isDeleted;
	 boolean isVerified;
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
		 return "id " + id + ", username " + username + ", password " + password + ", email " + email + ", phone " + phone;
	 }
}