package com.user.action;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.user.bo.impl.UserBoImpl;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceTest extends TestCase {

	@InjectMocks
	UserBoImpl userBo;

	@Test
	public void testGetFullNameFailure() throws Exception {
		String firstName = "Kiran";
		String lastName = "Raj";

		String actual = userBo.getFullName(firstName, lastName);
		Assert.assertEquals("Not Matched", actual);
	}

	@Test
	public void testGetFullNameSucces() throws Exception {

		String firstName = "Raj";
		String lastName = "Kumar";

		String actual = userBo.getFullName(firstName, lastName);
		Assert.assertEquals("RajKumar", actual);
	}
}