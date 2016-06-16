package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;
import com.sales.app.shared.organization.locationmanagement.State;
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
import com.sales.app.shared.organization.locationmanagement.Country;
import com.sales.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("AiXiXbc25ci85mMSgH1VnU8gl2QftQky");
        country.setCountryCode2("F1H");
        country.setCapital("uPVcYCxXUkK91Wi8Db6XmjhBzQKwT3jB");
        country.setCurrencyName("eJvBzwMZLaFMFfagX9QVXjjcpru1gmkzBJqdbb3NoBcRkLRy7m");
        country.setCountryName("2gGpQK7tEL1zA2i6Pub1foM9kSSyLlO63gxdRfTJetWxHaGFNA");
        country.setCountryCode1("YLS");
        country.setCapitalLatitude(4);
        country.setCurrencyCode("0t7");
        country.setCapitalLongitude(9);
        country.setIsoNumeric(158);
        country.setCountryFlag("6GmnMUfoRUmc9Kzr5fIatzDrNQUNX49ZPHesTtohXYttqSbSxp");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLatitude(1);
        state.setStateFlag("53sbmU180jnKhEUmIv3j8wfidQWW4yYeVkILdpmPsCgqajuA5i");
        state.setStateCapital("6cq7I6i9z5Rc2hCx3WN2BoVVp9hY78Y1CKuYKavgqWzdm4wCq8");
        state.setStateCapitalLongitude(1);
        state.setStateCode(2);
        state.setStateDescription("qdyQHru7I4iQ8DYT3lXmxeXkYurkdzMeAONXlmncUWrD2UhcKj");
        state.setStateName("92QXxJ3G2rF0PZV4gJj4LA9m0JLnoizIMXehNqsG6AaXmNBzXb");
        state.setStateCodeChar3("AAcAHXP7znY81Ld3iOGN5taHDV51mfri");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("4ocHFYiwNfoMFn4PpPm98L9W29GxhnVf");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapitalLatitude(4);
            state.setStateFlag("kXJM9h9ofwTOUe2hN20tOJyYmOleEbpLo3qbU9tcvacq0Rqcap");
            state.setStateCapital("dyKCc6mmjJEvWBl5WMf4p8QPCc3VtQl77DMpyhrrtnojimf7Ja");
            state.setStateCapitalLongitude(3);
            state.setStateCode(1);
            state.setStateDescription("kG3byNxx4BUOg3iYbTwuqMeqNVoCEcTCVqD5tbIBy9WCopZa2O");
            state.setStateName("nBVFc0RMf9bG37DCeWwUN7lF5I6vAG9soFuIaUk1YeFPhkt80k");
            state.setStateCodeChar3("MxY4Cy23mvGxtEU10qFitRQ6gCSpFkhX");
            state.setStateCodeChar2("Ho7fW519W5AoX14LzBR6B3yvMzzDl28x");
            state.setVersionId(1);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "dK75bxhpmsARPsV4LBRd4p9SGiGpmYNHwwgOtBW6DvuvPIAxrNNA348voHRVrdjNR2D9xpT2eIOu2PR8VPe7lj6lLra2empt1uNvksZzxt2QfzG53xKcSgXG5KrAWRkZW1mJQT9sE8YTAHwSlwMlQWoBOuUcS5F9s4QsuM2DK7qTdJBBZ88z02dtpEHIWsNSeanS3oVWZhSRfz3XVFqzdi5WFCgSGv92HG2Wh8zPNAG1dDpSbPov7Q0es8hz17Olx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "vShocjkh2rOWSlu2zuHHNzjIYJXEo1XuC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "4c7fEr68kfkrwPdd0msH6PWdJx3DzMaRn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "mZqJdUshah9jVnFKKKc6LyaFfh57l66Ta21JihyZWwolfherYTKBBHTW63RUmZkYmV9reiHPdMz91oIpfsL6ZTeWD70VypdWhjHWtlz9a8BMDwlzzWuQjWLpshikCsKG6qTO3fyUHE4GfIGDP5Pe8bUugwjOJv9sqUcGnjf6AyJzVO1j2IuRIc5NO3aVesKVqaBoDSkTcDlj4gMmLxk4vA5xluujVboxGp4aZljvjSKaA54mbGMmPIv86uG5JnXfv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "jCdfFByRTHJbb9y5h6FMGPCLTNzsBZviWKBdjaOp4LrrH1nGwl2PCDDXFtPEjgZEZVy58U5qMA3iY6FkpeNR2rP6U3Ga1qPkNtP6Ne3MwXBF2jWY0WlklGIQIA34e0hEP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "yjOim0DwBQgPWGfRDgjciQBF6ZCoppRdOd10Iy5XrEU1D9LOeFfBTg1VDtUqxTTP8AeaUp9gWoYPfFTQKSoQo83cYSW3mn6KArK5sbQHeObZQeZ9VoVrfzdoVZMRCvmdc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 22));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
