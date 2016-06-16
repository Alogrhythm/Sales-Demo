package com.sales.app.server.service.appbasicsetup.userrolemanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.sales.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuTreeId("GIyeDNctPADdZKTQfSVnVkjl40beGj9VJSWpxG0QrJZICLy52f");
        appmenus.setAppType(1);
        appmenus.setUiType("Yfu");
        appmenus.setMenuLabel("9O1YqMv5fRrpgWaPUI68Bf7QeE8NVnJLgarQbQ27yFLHivp5No");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("l24C3EcT34r1CK5vcMN7gtMGFxYet46DMcustgnqdUq7qdLysj");
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("GNhwQyclWbCFehtMrNn0y5MAuiEnMXrDYYAKbKlP8NPcIGhwfh");
        appmenus.setMenuAccessRights(2);
        appmenus.setMenuCommands("ysZWwFJr25P4kM98T8WiYXQbBI1qi0qrf7XpSNzNsAqpABxckc");
        appmenus.setAppId("80pKM8m2knARmkalY8P26YakiRLYVOwNdf3quZ8tOmcbnIH01G");
        appmenus.setMenuDisplay(true);
        appmenus.setRefObjectId("tCyL2UXcRDhpCEvO2ycpmmdHkCqHFTyu6UXjI58Mtuiw4ztfUu");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuTreeId("ME55iQv1RmvHy44SM5LnWrbUwsBuGZCaaBktffeO3b7ZubCe28");
            appmenus.setAppType(2);
            appmenus.setUiType("0Zm");
            appmenus.setMenuLabel("N1I8Y8RNsmLDfBuzQDVtashngXpNAQEJZavaCTwGLidZNOHQes");
            appmenus.setMenuIcon("J1RLqWlxVW2v5Utb1gLhFkwgyxMgX3frMmbANQBCAP5HMMLapA");
            appmenus.setMenuAction("44KAko2rmkO0bvIrozmuNfam0FBMJFC1Ny2hcis8iwP2AvIGVR");
            appmenus.setMenuAccessRights(1);
            appmenus.setVersionId(1);
            appmenus.setMenuCommands("20fLkaevZvva06bdoNAT6aHUxt4p0Hx5mmh21HjwsgFsJxaVq0");
            appmenus.setAppId("4dcto2fWuXaGN5ntZ8rsUV0y5os0LCdNfO5VMIH0US8DZlLjJb");
            appmenus.setRefObjectId("kNGiNFoQ8dWthi6nn9F42aUwHMggtuOEgysGZee24WtHnDT6aK");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "spT54z7TQxaVQavJJTcZj7vozyQkzLr4aKgWMMQNNh22llJqj5Aa2OC2qqWvfcs9sV2G0g9ny41mV3jv3r7kxZYWVDkF915OdESKityfEGWoPTCKsGJthCjB07rOK4bCboszpvZyR2Vv53CLtzO9CZRb24cogrsRDqTpeVZUUT6g09d9fIu92PNvq1mUidWf3zA3CPQW8ibCxGd4mXzo7lB9A9nf7JFEDLftNYqUqfm6QDctfCLbefUSBAJIq0u7H"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "StuknEJnA37FgtAU7UdRroSQ2fztllelzxqjRklMAy03r7WniYrTUn34GwlDXmnU7RSGN6aTfV0Ip8rImMIao4tkpFdKuIFcKOE5KJk1Gmgqx4wTNvVYlWK2SLWqIXO4NNiA9xBXgW5HvrM3oxt8YAIPbcBjqXv1MZnHXWnM7sgljH3M8lIoKVVdyPnbp14rpbu9x94r6yPNW7f1t9LCdUVyMV2pvT1fOVk2fYgCR7DVE5itecKG5oGqII0XYVWrv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "h228H3GpNIABr5dJreGZrIn2ytJP4jngY7TVAVw2rRbKcHYgHjM7JbMC47x6AWyuXE9LElXRi41cqhybxe4upCJraRzpPhnsZfphTnqdHQNvr60yZER7q3VGCUABpkC98kTKTy9SWkB3MyhDfovwKzB0oHW1tjszVgb1lqjD65aOZfft4RsDOyujc6pVeX0gl2bPhydK4OaZizBrdc9WTB5PQFH5p21P2JvjfSmDGM53uTXs1uzkeUuD2dawS9irJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "3SzUR9HvKQ8Rf2Wriu9rq8oQgYuBQIUd3qCochPKtjOVeCfttLfY6qB2FiC6QfOrx"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "b8TJ4mdqbSDCbAWo0CjaXACBoca1AslNTpEm7efed11Q49b0fmZAwFEK6O6C0dcsYod5Bnp6ZPhu5ZAQLfi2DCdeYZdyQjJoj9SCC3DvjCWE3HbDQcmAx0rWk2XVL1s3a1rPm2UCUYqEUL39HtiE9KwLzJQfxSRAtFfQaSJlAQLc88kL1MMMir76zSEezsZ8YLkZGMLdTnD27EyzPcuRLeDQMAJ0DXMuxR6MN2qHfTJqeTfIEzRqATiWS14jBMer4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "lgkq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "llqEmv8Hgz8aRJHkKnbHaztLerzY4qJeGbSQPxQPSGAkiwdcvHF51oGvlDtzZ0AeDgAsiQyDkml2CB4lBF6DGkII5WlxkZB01zGgBE8EUT62p090jcnHisMO3Js6jtVl0tz2Mjp40xGy13EXaMshzq9UMZFOguQYwuoUBAxqQUYTVKqhenAcQTddTaq0OlkzRb24WxrbyefLEmDFVc1DAaj92pgaImZvDjuXF6hm4m0n7z7QxYCO36vHhglEYhAQG"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "86rK4uWbiCilvWVqMRgV7Lsu01aG9bgRxiWh1aqlsYVVXeXm1dlu9shz6TqDWTY7vpQEhyjBXPbjTzK0Ht9diyDK66oSsA1eezRx6zNU45uwzdJ4f27ZmyPkXfvVQSPStwDvTT9Il9tCWOFtl7XQWYkv6vHQwMYvL3L4YqOoPVEvaYfQaBMG9cWEwLcNmaGHpeFoTMOGhydMXDSprF1F3AAO8uI9sHSwHWPelpEY4KrNOBGDqWrCkknbGPHDH59vE"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
