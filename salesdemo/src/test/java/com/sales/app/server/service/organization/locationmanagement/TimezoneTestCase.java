package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.sales.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("R1tHer20GDW8RXM77PVduQyg71fTMRRMVLJY4QCQ86zx1k0jWW");
        timezone.setGmtLabel("5NNhxZTXeqOvDwOFKzqtaiXdRuChvRkQUADfKlnjFLkRLsdurO");
        timezone.setCountry("Ct8cQLFIFjASKAByGmEGouIo8h0rJJXpYgOD2l583Fgorjk3Ld");
        timezone.setUtcdifference(7);
        timezone.setCities("0WPpQ1UTCxMCfLZMALtPYtYorvb0uI5E318ZdLVkkXMckCs2Yl");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setTimeZoneLabel("r7VQA2eSxrVlAwG3iIEPYyQImIar5vkn7n5LUpeD8120iNZ509");
            timezone.setVersionId(1);
            timezone.setGmtLabel("D5uxC3PayjUpWTMSPx7dfHuFQfwruB6L9WK1IZTBumu0AImW0I");
            timezone.setCountry("BwLSxv9g2jlsmEaCudOM9iVYdoAOLZpYaj4bg4P1CPxJJLhFWD");
            timezone.setUtcdifference(6);
            timezone.setCities("iD5fFOrhdYvC75kSMPo9JitNfYQpUeElEl4NvukdvSWerOwhAG");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "lqLcYdBSXcQzUOo99XGgsOZdeGt7JLfBMeDR7DaVYxWgFgNGETt85x92KaoSLdJ5bkANCEOLYpv1n15D64zoyiGxqdW16bvBjOGF7tpH4ceYdoRRZTVff6shjqS2sOCQJnMBLEG8SbIVzZbEdW2pGdThSdWge3cKZQ2PFRTefraONuOTDmxRVhh6b9fN15CsmfLBtCPxG2nMkMa1IGXgFggDdjHbhSsa27BZ1jaDjBWPoD6OJADP5iy4547uZc4gn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "HdBWTU32rgryH91CnqTZpH0RI7cjhCdCcA3vgZiYCGYBCPhlg6gyjLHNzaNRj0iiCZI4kh0AnIpaHHfDAmtW9AjgsRhKLTplef07Gi9mPLwAO64dgUKtQCgfMvHs6lLyjxrqqfDvqX36hCfCmz1U0ZWy49BSSVRqJ01qt8nwbh2yNPPS01prDngJ14t7bECLhZgSnGk7DThswuiXBlGzW6S9lRC0ZOF4apvQIpcQvEjK7i92gkrdSP7V5Q52G2F1G"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "zM0Cz9GE9CAanP3EffR10coAeCizWy2YApLH9wgizp4lHn9aEyRUewgkFa0qY97vi40gRrojvUyQmKze5Iv2hw1kQtILWdwJcTj4IEbOKndDXi5cpguKKgkTpgQD5rZ5UTKltiiSX2Z3eYOVF4AOnVojNTmKnQLqlcvXGJ3SRiERQ9koF2NCCUBZ4nohLYxP3mXyWe8n3ijZTMWGDi5WpNzKRt6kpMUK35P4uf238Mkc4UZ2G24hGLWoObydGZqsk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "JF2YfY0XWtVNvrwZ26Hp6G4CdxmK0NXfdvQODaRouKtgoKppaA08X9NiIcRhsJop5BZj9zAKar2dg7SiHIVj0eOlU0X3PlehJqzk0L1lN4AeoNFPbYIm965k6odwecFgrng1QYyGdOTvjQNE65PDLqNOm3pKMlDj3q3iRAOhmpA0X9FOJxbSMWniyNFz8GUXByMlNJIXSX1pH4pZIyqq3Qr9er9Xl3KbMpbvizjKEUg6yiMyzailEqC0nfbIdG1Eq"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
