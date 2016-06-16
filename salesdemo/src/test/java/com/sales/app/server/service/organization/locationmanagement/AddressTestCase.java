package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.AddressRepository;
import com.sales.app.shared.organization.locationmanagement.Address;
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
import com.sales.app.shared.organization.locationmanagement.State;
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;
import com.sales.app.shared.organization.locationmanagement.Country;
import com.sales.app.server.repository.organization.locationmanagement.CountryRepository;
import com.sales.app.shared.organization.locationmanagement.AddressType;
import com.sales.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.sales.app.shared.organization.locationmanagement.City;
import com.sales.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCapitalLatitude(1);
        state.setStateFlag("y2oVdzHBPlPMuKTQNCObWs7h94C1JEqRt0Us0FdfL4Sm7s59KP");
        state.setStateCapital("mIL1lgO8dNCCJbfP4BUhueFcP6vJ1QTwJz0Jq3mUXeyOBy3g8A");
        state.setStateCapitalLongitude(2);
        state.setStateCode(1);
        state.setStateDescription("4IrPMyCGYkuNnOmEqw7eorgN3SVaCAmHocsHbufXjzD6GUZYOZ");
        state.setStateName("rX17IgC8y3BYOFYsHyKWAaxW4tpp81RjpOGV99BsRvzw8UzCzQ");
        state.setStateCodeChar3("ybnKV765r4GZv4o1TMQ9RMsC4Hxxpcow");
        Country country = new Country();
        country.setCurrencySymbol("WS3PuHNA0lDB3ZVqAYpWIlEK2qoqj4d5");
        country.setCountryCode2("k1h");
        country.setCapital("xycoTs8W8GsSUYY3rR7azfCGdD605i73");
        country.setCurrencyName("NdoINXiEYvag74Tf4wTdGev6XARFobqBhq8yzShQkUHe5kVyKh");
        country.setCountryName("fEry4BUoCjm3AGy02KWFsIMJROkckC97Se7y43eEJi9zMOoDEP");
        country.setCountryCode1("412");
        country.setCapitalLatitude(6);
        country.setCurrencyCode("mUc");
        country.setCapitalLongitude(7);
        country.setIsoNumeric(505);
        country.setCountryFlag("oJZlvfl6gINmAM47Hs7juZT9SwCzGWvKoMtFZPtWUrxTbPy6wm");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(8);
        state.setStateFlag("M3Tam8GBn0PjIGqErrf6ywWEXVBQsM4jq7eFZUps7GNNcj8Mlh");
        state.setStateCapital("mCwyT0vZq1yoW1j7qo12GHijaR97VeV9xDupRSSfaFqq4N4qoO");
        state.setStateCapitalLongitude(2);
        state.setStateCode(1);
        state.setStateDescription("FwN0jcSyoSq6KSPhuy0D5u1AkqJaVCbCODNNICLx2CqA7ig5K3");
        state.setStateName("mkujz18uFsNw7awQtaAKfiargvCZzOaXOVAJ9ND2jX1PnP7TOC");
        state.setStateCodeChar3("DhdlebJ024XvOMMd8kR8LGo0DMX5CX76");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("NyIDeGxmug4hnRbKc4VIpvTvbA30U8ao");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("pvJCq8hWwOQXCXTkYR10vytv4OwZjEoV8u8lF0VPoIRMEnOmn3");
        addresstype.setAddressTypeDesc("GOIgwcPd3SavuWH0AQBBbzIv0EHnIfJEEeBoosSEyTjcDPSSSd");
        addresstype.setAddressType("V1Ph8F9ZFGNEGR8w6y78oKqkEYTRctnG5mbgZnC4LI4yxnAg1d");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(9);
        city.setCityCodeChar2("nTGWXenoFKrPVuqLKRODx9XTXyUYglxa");
        city.setCityCode(3);
        city.setCityDescription("gyYCqUifdYzPktrnPpH8arDgYBNrYsMWAyErLQE0POkG2EpleZ");
        city.setCityLongitude(9);
        city.setCityCodeChar2("1N8LnDZk1SdvYDXrSDzPmxx6HVa8slZF");
        city.setCityCode(3);
        city.setCityDescription("Nr8cI4kHz3g4ervlMVS5tGzTWNrmbj3wNPwGxXJKE3IVEQXRcS");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("Wwgf8m7hpwAE6mNB50Ho9lsfNnYEaPYOIj4yUcuT03sIA1rGCr");
        city.setCityLatitude(7);
        city.setCityName("BDXSQhyqReeEQx71Mkgj3adIeEoinpLGMll81jyKGc5CIkSdSZ");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("nuHxoZcxUMHViH7YHeSp2OrhB6fZlWH6g0cc4xX52aKnpZIbeK");
        address.setAddress2("AUcbPlfESnQlOFP9iqWwZQM2qaCe5gcUve1I1CQ9K2YfdOtsLz");
        address.setAddress3("fMnjUBwGCAwP0c59XqqJpyXHN4WpQ1zEXvsRDDfS7910kbpRDV");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("8bQ1bFXhzZk");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setZipcode("58t367");
        address.setAddress1("AvAzDQE6N9fDG1vsChaI1DAjmPX2UXYamY6nP0AHYMFOMneTFq");
        address.setLatitude("3Tfza868zcHH3C8MNNCoNGxrNmee6cbBVtZ8xJVWsqb4jxqL0Y");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("ambog5QIHdvhbqWoC3mCFGJVqNl8MUXOlXnc3tYGZ0Hy9xKq70");
            address.setAddress2("1El8laomZchkjJVQcCWZKE7Bv2gut0452Rft1ZInIN97OkGq7y");
            address.setAddress3("WCkx0ffbFniJG9zvjZs45rrpJsRUinzUySMgspZVCVvty5Brno");
            address.setVersionId(1);
            address.setAddressLabel("OEIIFj5Kxgn");
            address.setZipcode("m63g1y");
            address.setAddress1("bCaqRx81JMkExyDiE0h9c3dgWmB7WKjLvrL8ccJdHJNZMS25w9");
            address.setLatitude("RtrcwLekfeFO1XvHRc0bSCEie77ngBOvG4Pldc4JAvCqp6HFtY");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "nj8aVlHV4qn8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "ZHNyzbClms1AaPW5tKpaSsHgobF2Bvzdw3Gy19puGZVsKryryeyihtdcP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "1LIIu4UEF6wxGo1HBdR4swgzc6TIwwQ9egsaKAe7PAzXzeZZ7KHApuEht"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "WJoDRFbvOGCdEm5yZgHL1uDIvOAARXaA494z3UtgC0cJAV9FqrtBNFSwl"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "k3qdMGN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "fkYMn5khG7E5t5kDM8tPGLWGXOUzAcqVJZUsyZyVutg1flZO6dMndldTS8FDcHWBL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "MD5ozQTVdP3GMYDUIifc8GDDbycIdjbJIh5VwL4Vmwth1nVh8V4JcMJna6YAZM2Gp"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
