package com.sales.app.server.service.appbasicsetup.userrolemanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import com.sales.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import com.sales.app.shared.appbasicsetup.usermanagement.User;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.sales.app.shared.appbasicsetup.usermanagement.Question;
import com.sales.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserData;
import com.sales.app.shared.appbasicsetup.userrolemanagement.Roles;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsLocked(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("W3XKNwlr0U43g8p0VQNasTo6WojizQxaDp6AtBcr1EK0IDqCJq");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("ckwunkqsiGUjWBaodw0qMJvKIjOEkZ4NTiH90FK0aG30mo7iz1");
        useraccesslevel.setLevelHelp("XQSp9wlMTmCdJAyk0WwceMpeasSIDH3M6fU9TfonmN8ZC3SJSt");
        useraccesslevel.setLevelName("TQeX4Cyk7jGNgL2azSFB6cVO0XLpdQJtaDEBezQ8vBMydrWFnW");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("uzRLZHABL4uR6a3d7j7ZQYK7SKjF9LUHMHYUhCAcs81EkYIWN0");
        useraccessdomain.setDomainDescription("mDNL91ARiNjPe8hN6D8m3KuZ0czoXVP0hyCM9dyPldSYzi85Zj");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("mXTRU9Ok8Ld3lXGcG2Iez0FENFAPpZuMS3H9ImjOmAWgBzFgMN");
        useraccessdomain.setDomainHelp("rl9WN5MTpH8FV7JFMewTMxZlPjIysA8qJQIR4I9f8nkDK3r6Di");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(3098);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(2604);
        user.setIsDeleted(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465991082548l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465991082548l));
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordAlgo("Iud2AnF8CyyHQhgt9bm3sQP3BfJGFS349COBEhLx3Hn6711jK6");
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("SDstYVLfeaMTwizFUAM2txVT06w6HwjjLNwfGS3Fkum87USD98");
        Question question = new Question();
        question.setQuestionDetails("BCFjmWVWB1");
        question.setQuestion("CfT6oiRYbJ1U29JbzGun3wg32Dyh9Il21yn57KDn84xY4kdxnC");
        question.setLevelid(1);
        question.setQuestionIcon("mySYyXK82wfDaVfh3c1WKlkrBHdll0t9Xko6NYlYXyOe55k6Fh");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("PAwm3GUigZEOOA6FlbD99bj36HCsRfqMbV05BB2SOzkuoai0hF");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("Df6d9s23ULFTHgKXIoyj9wnQttrWqN8088gvpmTzOFTyK1ufz1");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465991082731l));
        userdata.setPassword("rWG5lWuMni8wFEkUWlHd9loyTiAgYmfoIrh30swE0A63Ti9PE0");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465991082741l));
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("iKmOYGl9tXwWnElNlX1eEP8TssdUeZ7A");
        userdata.setLast5Passwords("NfHdnGULraBBM7ubmYdOwrCNQngMhZMyoyh2KMuV8TkQH5K5G0");
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        Roles roles = new Roles();
        roles.setRoleIcon("YqIxyOJKtZuhnO1lLonNtQlMUmqLHnN3HxZkiVT9eQ5eJYamkl");
        roles.setRoleHelp("RXv41gqj0GvYTDnqDnWMciGUfGuXWTK8tl6jyMwBSe6ct81Rox");
        roles.setRoleName("65R5Kth43AIwI99pXYqqlUOTADkEmYo41DeR8zYyKLNjJsinwx");
        roles.setRoleDescription("NZY8LgNUw1ZujHbnZMlL8bqYzWANFCNnJ4wrlGcyLCualIuppL");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setUserId((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.delete((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
