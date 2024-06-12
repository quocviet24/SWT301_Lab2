package com.nishikatakagi.ProductDigital.dto;


import java.sql.Date;

import com.nishikatakagi.ProductDigital.model.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserSessionDto {
	
	public UserSessionDto() {
		}
	@NotEmpty(message = "Tên đăng nhập không được để trống")
	private String username;
	//@NotEmpty(message = "Email không được để trống")
	private String email;
	@Pattern(regexp = "^[0-9]*$", message = "Số điện thoại không đúng định dạng")
	private String phone;
	@NotEmpty(message = "Tên không được để trống")
	private String firstName;
	private String lastName;
	private int roleId;
	private boolean isDeleted;
	private Date deletedDate;
	private Date createdDate;
	private User createdBy;
	private User deletedBy;
	private Date lastUpdated;
	private User updatedBy;	
}
