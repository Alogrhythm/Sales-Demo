package com.sales.app.server.service.appbasicsetup.userrolemanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.sales.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.sales.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.sales.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("FBYscMTLLFI0stABbhWqd7MBHypVWZe9ksuTa4o4YNYAhagZnZ");
        roles.setRoleHelp("RBVynNgnTp1D0srm30GRcR7K6PP1tCeTOvhGeObxlIW4DEgNhB");
        roles.setRoleName("GhF0xWpk4ReQ7CdIfPIWukQxtcnUmtMU5AtV3v1p6PNxfmozU1");
        roles.setRoleDescription("Hm6MdgIshyBelUKVObspGuBQaXnekILYBJtFAbE3E7qdCP74sg");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuTreeId("LFrqBya4cVmaytXblw1r34FG4ImTL53Osj1Dcm0IxZw76kuLNT");
        appmenus.setAppType(1);
        appmenus.setUiType("NCy");
        appmenus.setMenuLabel("EopolvJO8i2aJUXHx7jNGi5JyVmvQuIlhNXJqLddM5tb3mwxaT");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("FCVioSK0gdjOAWqKDYqXYUHP0yf6XmSm2vPnYLdVmhieKUXbH6");
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("Bq84qJO7upAFmd0yOO2hu2xpK3uxUQugXo3ZQjX7weNCLjdx5p");
        appmenus.setMenuAccessRights(4);
        appmenus.setMenuCommands("qmD01IN1lsRi1tu5078y7eIwRRfAUZICb0VNpyt6BrALuGIoPS");
        appmenus.setAppId("gv9c3YMzNffqmCnNxZCNePutWkS0Ibu8tEUUY4MOiMUiTKWpSG");
        appmenus.setMenuDisplay(true);
        appmenus.setRefObjectId("SimIe8ViQ19c1DjLZEKqCRQJigwEtOR3ImTTqs4xvytcsxo3fE");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("RWusScE9kGCS4SZmoJq9so4Pr6geYP8JACMpEDFctWzVoKGAlp");
            roles.setRoleHelp("ip7J1rJ8actLRC9GLsmKbqzZm4wbVNAZ5AC00IISFMOn75gYP1");
            roles.setRoleName("Ce9SxyWVryjW1HbuBna32Gdc5GKCkZAII22rxbxXCOfyeStvNQ");
            roles.setRoleDescription("Qe4Jgtr1XosnZ7P3mZ3YbmlHx1EqTFsNlzvg8r9nnLsyObDpQP");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "Jol7BY6L9efhizWpCfH7U22bdlnM3EnrFilmRVELpZJuM9TSUbhfWcvJVB99AtTqz2poIKMkGwPEmOUQdiA2QG1rbQoO5UjMhsgg7YDn8CgIcOC3eoPBwDCwFaMKVSoKcCqQAAgZQysINliHD84aCoJokXXpuKWakBBLydo9uDIRAQfdsyNdiBXadqpElqMTVAGc7XRwwiqHtDOxRTJs9ueQK4lkblJTWVhlDEEUAAE3w9FTGFCbhpnPpfcVT1cNU"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "u6CW8enWRr5rpLuMyyYjfhIJX4mIxdabjjJKd7ADArJPPFJ73sbJ14OT5JOsvTpSrFo0F1DxoqNfs1fjHYe94J4pyrLaolHhHgeSB9X6qYJ3wWkeG0nyPhpoY03VhDjfZqsNbpmCaQjX9ndGybLY4t8tbx6c2IS4oqcQCjD9ACT6KexwM39QTQyEvPEKWjxje0Z3xhSLs8b0mWVGie9uE8AaP5ayP2NzRtbR46CkuNLEWiXIB3goOR6jsl3g3w8aq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "jFkUpaHR10L8ObYBeSeI2T6ErBDHyj8fAtcNXvO7Kkr09qaiR4XpCZuTn3AGe2oV4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "4E982NbXgFwm3WrKALHy9S1wUHJtyrCnUFaveFzime83B74ANls9L47Dcu0M5D4cvZKJqKvXf5knLbOzZacFi7APaaNi03cX5kfL9PmzdzdHbSu7ApfshfQsWzKjpSB9QQeHwUSkf3hc3isTaAZhvCQMqo1Z0qH8OUILT1EiCwXcmv2fp8fJ4w0xi8ooGScYJpJf6AIFEiSTzEkP9d9lmVq2pWohQ4luRDz3CA8W6U27Dn9e2DmMUkZbToBynJo0E"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
