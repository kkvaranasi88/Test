package com.user.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.user.bo.UserBo;
import com.user.bo.impl.UserBoImpl;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class SampleActionTest  extends TestCase{

	@Spy
	@InjectMocks
	UserBo userBo = new UserBoImpl();
	
	@Test
	public void testActionSuccess() throws Exception {

		//ActionProxy actionProxy = createActionProxy("/", "/useraction", "show", null);
		UserAction userAction = new UserAction();
		Mockito.when(userBo.getFullName("", "")).thenReturn("result");

		String resultString = userAction.show();
		/*
		 * String result = actionProxy.execute(); String userActionMethod =
		 * userAction.show();
		 */

		assertEquals("success", resultString);
	}
	/*
	 * @Override public ActionProxy createActionProxy(String namespace, String
	 * actionName, String methodName, Map<String, Object> extraContext) {
	 * 
	 * return createActionProxy(namespace, actionName, methodName, extraContext,
	 * true, true); }
	 * 
	 * @Override public ActionProxy createActionProxy(String namespace, String
	 * actionName, String methodName, Map<String, Object> extraContext, boolean
	 * executeResult, boolean cleanupContext) {
	 * 
	 * ActionInvocation inv = createActionInvocation(extraContext, true);
	 * container.inject(inv); return createActionProxy(inv, namespace, actionName,
	 * methodName, executeResult, cleanupContext); }
	 * 
	 * protected ActionInvocation createActionInvocation(Map<String, Object>
	 * extraContext, boolean pushAction) { return new
	 * DefaultActionInvocation(extraContext, pushAction); }
	 * 
	 * @Override public ActionProxy createActionProxy(ActionInvocation inv, String
	 * namespace, String actionName, String methodName, boolean executeResult,
	 * boolean cleanupContext) {
	 * 
	 * DefaultActionProxy proxy = new DefaultActionProxy(inv, namespace, actionName,
	 * methodName, executeResult, cleanupContext); container.inject(proxy);
	 * proxy.prepare(); return proxy; }
	 */
}
