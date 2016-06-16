package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.CityRepository;
import com.sales.app.shared.organization.locationmanagement.City;
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
import com.sales.app.shared.organization.locationmanagement.State;
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("T8t2PkmsjMayq056KrAvvdoQ3MIj0l3h");
        country.setCountryCode2("UHH");
        country.setCapital("uGEBp1MvzvPG6M6z7kpc0RA3xcBrM3aF");
        country.setCurrencyName("VKHemaduCIJ4hiea6hpmSF65iM02yo8NIBelMOfpqTGa8BNmYK");
        country.setCountryName("x0h8EVSGIY86LcPzir8WLzDsE0mxz1DF1P6RG67WDlHxk31OmZ");
        country.setCountryCode1("7u7");
        country.setCapitalLatitude(2);
        country.setCurrencyCode("oTu");
        country.setCapitalLongitude(11);
        country.setIsoNumeric(961);
        country.setCountryFlag("Emq7OuFtn23GgPVZGr6K0e0kCfEx5h8ECcyQmt7N2JZbHa8qKc");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLatitude(9);
        state.setStateFlag("qC3wFCxvwhdhUq85JDmk2tLk0QadazbK6fmLoSFOMCZBsJP5tM");
        state.setStateCapital("5ogX1sTK8vHZ04JhtxHrLIKRKuN2tMQgyb84mWgICOO57GBNsy");
        state.setStateCapitalLongitude(8);
        state.setStateCode(1);
        state.setStateDescription("9ldaPPJgw7lDsVSXzYoPtf22sa4t55VbY4X1RAr4Uq53tjWK2l");
        state.setStateName("t6zRNQRsiBnN4GljLui2qbtzLkJTgy6jYekS9MiM6pKYuIeB5B");
        state.setStateCodeChar3("QQahymktmTSFILIf930cisk8sw6dvCv9");
        state.setStateCapitalLatitude(5);
        state.setStateFlag("90EH9N16XdotupITQVRqNs3zErnY1EsSO2KcQQ4YSMeezUR96q");
        state.setStateCapital("GwQgahrLFxsjUTNJ8gpNOKT3TO8BdWgWLUFoGRViglTljWr6tN");
        state.setStateCapitalLongitude(7);
        state.setStateCode(1);
        state.setStateDescription("Eyy52KkfDCNNYODY8mcm2asGQKkqttofm89hzXdvNq0pKa6oVb");
        state.setStateName("whxoe6rJ4lEyWoJZ4fXzUJf9myWiwcOoSzQA1DhRPi7mUcqQhK");
        state.setStateCodeChar3("YEXLKvgWSvViAYDcfawqAg27qRFdqQqH");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("5KUiLGL8rGowJlOquHL7bwoFsYsSSdiT");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(4);
        city.setCityCodeChar2("bhAkEE9He5lKd5UIBmRZYATJe9Makidm");
        city.setCityCode(1);
        city.setCityDescription("Ldp51h8mKkucc1lLW3KLhZ8DlP2dQf2kFmJ6VU0FulqDvd1ymr");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("GUPTp1A8MYF6KIWrtejxL9xpnexdREHKDN1PXnOfMLxMEKXPsx");
        city.setCityLatitude(7);
        city.setCityName("PIteTuu82va2F4IS7lcaVk0Q9Uij55DO8FTAcpZOXeyKg3oM1e");
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(2);
            city.setCityCodeChar2("3A8Q3cT5RtcISX2QnbGng3g7jVoihT15");
            city.setCityCode(3);
            city.setCityDescription("LTzofbL4AqwBclCyMKLikjpIBy7fR3LatJFgtt0TeC23Qbk8N5");
            city.setCityFlag("i9cWVTHx96d6KlKo5xmMQH4987pm7UJFs4YB3B5wkpyAXzQlh0");
            city.setCityLatitude(10);
            city.setVersionId(1);
            city.setCityName("DqEnEKSTh3TDec0Jj4DRcaKi1OFDKIvF9Oh8PDfKpuznmobYgG");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "6FxeC6pRTaTC24pA360Q0ema6GMGNFSOjxSNI1m7LRDvWXLf584QqsRjmZCNCMcUGwv3GM5CS1JZvc8jEYm6JBB0RJZja11HUeD57BXlfiupWBD3qtiYDZYpNvvaI2WUQ9aPz2ntune2TK5qmYvqka5fSyOrNN71rES7bB0mLw34Mm3cTY33g8BlXxUo8NTaBbzQjqyPdy6FGWz8CU6LebHnvFOZlvVS15DNlReHWV8AZIAqgeDrIz88zTiB2ZfmI"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "fkCZ63wQqnh3XlDFTIL4sDYJEpfHG6kwK"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 6));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "3n1bgeYuYZXFrBrvM3drWzAwXZZSKgAKcnnxGir67SD1HRzP0Yj3GWnIOHOSDlVj7kNS2pTFFWFHL0SWY2F4wUhePBSIDKxU6qoFRLFuwE7e8irLyy3vRcoJCGct0ZJAL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "qfK1TVKR5OC9e1YxvXkWgaLqxIZ9NWZXTG89D2k1Ny9foA4fHesBsNTv0CAMJ8N81ZZkZDZWevddg6eIAkPVmElDj260LqxAYHaqBMsUXmM0rqpaaveTuDz6RnLmB7eFv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 21));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
