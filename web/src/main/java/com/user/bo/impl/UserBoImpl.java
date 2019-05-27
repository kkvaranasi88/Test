package com.user.bo.impl;

import org.springframework.stereotype.Service;

import com.user.bo.UserBo;

@Service("userBoImpl")
public class UserBoImpl implements UserBo {

	@Override
	public String getFullName(String firstName, String lastName) {

		String fullName = firstName + lastName;

		if (firstName.equals("Raj") && lastName.equals("Kumar"))
			return fullName;
		else
			return "Not Matched";
	}
}
