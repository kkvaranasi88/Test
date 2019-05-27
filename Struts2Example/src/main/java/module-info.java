module Struts2Example {
	exports com.mkyong.customer.model;
	exports com.mkyong.customer.action;

	requires junit;
	requires struts2.core;
	
	requires struts2.junit.plugin;
	requires java.logging;
	requires tomcat.servlet.api;
}