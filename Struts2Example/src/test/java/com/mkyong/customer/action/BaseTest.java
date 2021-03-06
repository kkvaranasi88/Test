package com.mkyong.customer.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.util.StrutsTestCaseHelper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;
import com.opensymphony.xwork2.XWorkTestCase;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.util.logging.jdk.JdkLoggerFactory;

public class BaseTest extends XWorkTestCase{
	private static final com.opensymphony.xwork2.util.logging.Logger LOG = LoggerFactory.getLogger(BaseTest.class);
	protected MockHttpServletResponse response;
    protected MockHttpServletRequest request;
    protected MockPageContext pageContext;
    protected MockServletContext servletContext;
    protected Map<String, String> dispatcherInitParams;
    protected Dispatcher dispatcher;
    
    protected DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    
    static {
        ConsoleHandler handler = new ConsoleHandler();
        final SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord record) {
                StringBuilder sb = new StringBuilder();
                sb.append(record.getLevel());
                sb.append(':');
                for (int x = 9 - record.getLevel().toString().length(); x > 0; x--) {
                    sb.append(' ');
                }
                sb.append('[');
                sb.append(df.format(new Date(record.getMillis())));
                sb.append("] ");
                sb.append(formatMessage(record));
                sb.append('\n');
                return sb.toString();
            }
        };
        handler.setFormatter(formatter);
        Logger logger = Logger.getLogger("");
        if (logger.getHandlers().length > 0)
            logger.removeHandler(logger.getHandlers()[0]);
        logger.addHandler(handler);
        logger.setLevel(Level.WARNING);
        LoggerFactory.setLoggerFactory(new JdkLoggerFactory());
    }
    protected Object findValueAfterExecute(String key) {
        return ServletActionContext.getValueStack(request).findValue(key);
    }
    protected String executeAction(String uri) throws ServletException, UnsupportedEncodingException {
        request.setRequestURI(uri);
        ActionMapping mapping = getActionMapping(request);

        assertNotNull(mapping);
        Dispatcher.getInstance().serviceAction(request, response, mapping);

        if (response.getStatus() != HttpServletResponse.SC_OK) {
            throw new ServletException("Error code [" + response.getStatus() + "], Error: [" + response.getErrorMessage() + "]");
        }
        return response.getContentAsString();
    }
    protected ActionProxy getActionProxy(String uri) {
        request.setRequestURI(uri);
        ActionMapping mapping = getActionMapping(request);
        String namespace = mapping.getNamespace();
        String name = mapping.getName();
        String method = mapping.getMethod();

        Configuration config = configurationManager.getConfiguration();
        ActionProxy proxy = config.getContainer().getInstance(ActionProxyFactory.class).createActionProxy(
                namespace, name, method, new HashMap<String, Object>(), true, false);

        initActionContext(proxy.getInvocation().getInvocationContext());
        ServletActionContext.setServletContext(servletContext);
        ServletActionContext.setRequest(request);
        ServletActionContext.setResponse(response);

        return proxy;
    }
    protected <T> T createAction(Class<T> clazz) {
        return container.inject(clazz);
    }

    protected void initActionContext(ActionContext actionContext) {
        actionContext.setParameters(HttpParameters.create(request.getParameterMap()).build());
        initSession(actionContext);
        applyAdditionalParams(actionContext);
        // set the action context to the one used by the proxy
        ActionContext.setContext(actionContext);
    }

    protected void initSession(ActionContext actionContext) {
        if (actionContext.getSession() == null) {
            actionContext.setSession(new HashMap<String, Object>());
            request.setSession(new MockHttpSession(servletContext));
        }
    }
    protected void applyAdditionalParams(ActionContext context) {
        // empty be default
    }
    protected ActionMapping getActionMapping(HttpServletRequest request) {
        return container.getInstance(ActionMapper.class).getMapping(request, configurationManager);
    }
    protected ActionMapping getActionMapping(String url) {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI(url);
        return getActionMapping(req);
    }

    protected void injectStrutsDependencies(Object object) {
        container.inject(object);
    }
    protected void setUp() throws Exception {
        super.setUp();
        initServletMockObjects();
        setupBeforeInitDispatcher();
        dispatcher = initDispatcher(dispatcherInitParams);
        setupAfterInitDispatcher(dispatcher);
    }

    protected void setupBeforeInitDispatcher() throws Exception {
        // empty by default
    }

    protected void setupAfterInitDispatcher(Dispatcher dispatcher) {
        // empty by default
    }

    protected void initServletMockObjects() {
        servletContext = new MockServletContext(resourceLoader);
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        pageContext = new MockPageContext(servletContext, request, response);
    }

    protected Dispatcher initDispatcher(Map<String, String> params) {
        Dispatcher du = StrutsTestCaseHelper.initDispatcher(servletContext, params);
        configurationManager = du.getConfigurationManager();
        configuration = configurationManager.getConfiguration();
        container = configuration.getContainer();
        return du;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        // maybe someone else already destroyed Dispatcher
        if (dispatcher != null && dispatcher.getConfigurationManager() != null) {
            dispatcher.cleanup();
            dispatcher = null;
        }
        StrutsTestCaseHelper.tearDown();
    }
}
