package com.mkyong.customer.action;


import static org.apache.struts2;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

public class CustomerActionTest extends StrutsTestCase {

	@Test
	public void test() throws Exception{
		
		ActionProxy actionProxy =  getActionProxy("/addCustomerAction");
				
		
		assertNull(null, actionProxy);
		String result = actionProxy.execute();
		assertEquals(true, result!=null);
		System.out.println("result--"+result);

	}

}
