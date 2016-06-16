package com.sales.app.server.service.appbasicsetup.usermanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.sales.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("i0n4YYo7gyLgryjVd2HuijD31i4GfRrkggEzOmwA3yunXgaJzL");
        useraccessdomain.setDomainDescription("6Ep8RzHbacAR5HIveWNUOmBk2qEYktohloQgVXeUZ9niLElHp5");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("RPLc5jVxf6ZQZoUXthOSNrbjqVVmInY9MrTMGFL0ud0WuiXFoC");
        useraccessdomain.setDomainHelp("3Rba838PS4il48fRP1WOB9awUij2vuf2AKtpCJTfRsaHlQ5GWo");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainIcon("ucRhfqHZDciqFNvyEyg5wHDnEZ1n27WLGbu1QK2THC2nIc5kV7");
            useraccessdomain.setDomainDescription("IlX8LyDV3KhptLhoczzfTr6D8w1cropJOktLMvnWnJp4gvjNjV");
            useraccessdomain.setUserAccessDomain(65045);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("3Cd0XdDegWs71ayq2bJfqvBaUv63TivTC5Beh85H4zg0AmjRIN");
            useraccessdomain.setDomainHelp("lLZQ1OL2nqN7ijGfBo14ot9gbgDxisbWa9qGbNiTnp7aFiZjth");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 159971));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "tnrmaM69ZbJtNfxQJUlVdP3GqP9o1cGVS1csMPUCwd9o4cDL5obLI9YMilbfyxDXIIB8BUtmxBi8eA0dRXjPfFPKzUr2AYaO2VTaLC3BErCn1S42W0JbI0GbuYaZRWIyoqto0P12C7MdxoaTdlRwbdbsaouLIocqv97d0miv532vnP1LT8LCALVHVRB0L9of7OHDL7Rb071dR0OOVS21TtF4vjS0luMY3DYkpSKF4QeDasNTlMWoeoWsDRk9UltWZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "P8JVThwc8OlmYcFWWx3tW8QldvVH9qF24KIWUDHQtYu0pmYUxmYcknXtvNPiXHbRwO3aS7Y6wnzqcvn2JABF5xqcGJqAW7Vb1E9YzFQN6htZDsvZyTCHmxfuKDAWZBmD1vk3M4mgzMWS7QY7tZN4UAbGC2rgpDzsBCg90RifqkTtHpXDmochfXLabSpBTfX97ZkzeXiY7JZOEAprNRINO1LOhXsi1ykMiaIHfVFEhRUbNfgjOfIUMOJDMCHzQ1yPB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "e2Nm92LtNxuN5mlxeqk18atoSHUrvacqzghLGRswz5eP5rHRiRJp5R0o68RT6TnKZMYhq4IFGoE3OHy7ltup5yT1dTLZNgBAYaruFoJx8dPOVPm0V6gf0EcNdRtpBSWav2HirjpmnCwaTa9st4rJtIlFNdWNM3UjmKMZUc3cvZVXUzHFJZ3o11b8bZVMh4rqB49OACE0YQZ9XFgvJ9he2qEu9gtbBPOW8iRQja0YMc7qdXBxyKZpsMRtJTHkyrLa9"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
