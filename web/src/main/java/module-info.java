module web {
	exports com.user.bo.impl;
	exports com.user.action;
	exports com.user.bo;

	requires junit;
	requires org.mockito;
	requires spring.beans;
	requires spring.context;
	requires struts2.core;
}