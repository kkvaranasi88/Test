package com.user.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.user.bo.UserBo;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserBo userBo;
	
	private String name;
	private String address;

	public String show() throws Exception {
		userBo.getFullName("", "");
		if (this.name == null || this.name.length() == 0) {
			addFieldError("userName", "userName.required");
			return ERROR;
		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
