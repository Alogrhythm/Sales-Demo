package com.sales.app.server.service.organization.contactmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.sales.app.shared.organization.contactmanagement.CoreContacts;
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
import com.sales.app.shared.organization.locationmanagement.Language;
import com.sales.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.sales.app.shared.organization.contactmanagement.Gender;
import com.sales.app.server.repository.organization.contactmanagement.GenderRepository;
import com.sales.app.shared.organization.locationmanagement.Timezone;
import com.sales.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.sales.app.shared.organization.contactmanagement.Title;
import com.sales.app.server.repository.organization.contactmanagement.TitleRepository;
import com.sales.app.shared.organization.locationmanagement.Address;
import com.sales.app.server.repository.organization.locationmanagement.AddressRepository;
import com.sales.app.shared.organization.locationmanagement.State;
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;
import com.sales.app.shared.organization.locationmanagement.Country;
import com.sales.app.server.repository.organization.locationmanagement.CountryRepository;
import com.sales.app.shared.organization.locationmanagement.AddressType;
import com.sales.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.sales.app.shared.organization.locationmanagement.City;
import com.sales.app.server.repository.organization.locationmanagement.CityRepository;
import com.sales.app.shared.organization.contactmanagement.CommunicationData;
import com.sales.app.shared.organization.contactmanagement.CommunicationGroup;
import com.sales.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.sales.app.shared.organization.contactmanagement.CommunicationType;
import com.sales.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setAlpha2("kP");
        language.setLanguageType("cARdBRrdySrcElzl7ju9kWHwozi6Eayw");
        language.setAlpha4parentid(5);
        language.setAlpha3("Tqv");
        language.setAlpha4("RjZV");
        language.setLanguageDescription("YvGQVDO7rajm8kGcCS7aIsfB0QsVt7nVRGHDret98MesX4PhDs");
        language.setLanguage("WvzKLyzYwvZRSaLsU4yFx7d9suJwI84PMpx5is3Ky8qNxRv3dT");
        language.setLanguageIcon("ConzGExMdXirXuTbKh5fSxudUtYES0R77143ODZzyaUHUih43q");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("UJOI4mNHGtCfo4RKY5adWHnj6XnKWAeG5R9aseKe2LSH80tTCl");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("Mo6oiPlb2TQIJPeFYXYCixqlzhCNfQnesMQvx0Keh2TnBfDSBD");
        timezone.setGmtLabel("T788MiKyjftnGtjErtApDhwi3WqMMOTorNaqcnDIHflZgfj2dv");
        timezone.setCountry("YuykZza1z2T7d3oGpzSMDhNUibjzWBcdl2gky41lf62CLODErj");
        timezone.setUtcdifference(4);
        timezone.setCities("T00y0SDAr0lrkSZk51yf5caIdAwoXwNbzb8UqLGdy71UpOFzbl");
        Title title = new Title();
        title.setTitles("WcaXFz0U9y6hJV85a02trYFaGLyB5byDlVK2w2iJE9s2fRgtPi");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("L3IqGqLM2afZpWRgYZ1tseqZ4SS9jfBTVelNMvgnpLRbOqlUde");
        corecontacts.setNativeTitle("wgxt0HL1c2pvAbJuZJP243Ttp2y5reitNnAFalvLgqvmeptSdv");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("mw9erPdnPuyY7DIYl29tNUODDxsJqacaDpwyUF2muDzQpwR4EL");
        corecontacts.setMiddleName("txLCLhLCAe8dm0GjBnIXarCd8Cqe7a4nWJSGFtJMIwTYYIfDGj");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setLastName("9Cj0xOqbG3PJSGwcv1Isob02HE8VhP8f5DYmix5zL9s7Cp6sze");
        corecontacts.setNativeLastName("HikkuoORLxbIoarvfdE3fZqUPmac4X5UIljpw5xT9GHReUZCVF");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("QjvbF75VcS2ohCXjuCnMG9b6ipKYRMcFkdgUgHRyO7iNLSB8VN");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465991064254l));
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465991064254l));
        corecontacts.setPhoneNumber("njDRv1ileWNUKkxu4AsC");
        corecontacts.setAge(69);
        corecontacts.setEmailId("W4mCLTSE06VeOZKWh31INC5ekq3I9995xF5IK61UO8DWMSAwlr");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCapitalLatitude(10);
        state.setStateFlag("LCcbWF7uJdnrvipI9sjZcsAyftoV3VICDrXdB7YiSpfLqwCa0u");
        state.setStateCapital("w6KSToXDiWOzfAv8E1V7Dwfikt38lQCIOjJC6FopGICqYtxeK4");
        state.setStateCapitalLongitude(8);
        state.setStateCode(2);
        state.setStateDescription("0DcjypP8Qg92VlKY52JraZ3QtasZDJVeTrIck12nwsG1lLB2Hb");
        state.setStateName("qzwgrEdsCucDv0x5V5Zb12y75qDbIpVg0pXmagXYkNRlZYvVHY");
        state.setStateCodeChar3("OaWXp0liRPkYJ7J1W5LkhLzBMUPwwJYC");
        Country country = new Country();
        country.setCurrencySymbol("OMoCNL7VYoz5yb3XUo1l8aeugnPHOqUe");
        country.setCountryCode2("ZeC");
        country.setCapital("eANw5ZIf3aElpnjseoB8jOmMsxQ5DCdk");
        country.setCurrencyName("xH7abXXUrytf9dGKefiOwEE3Wiagubp0h97qk0pOp9fXq9ejQA");
        country.setCountryName("qNSDxoX50O8IWCXFCEPwiJgMfjT4Gma15MAPm1ydFGmr1dN2KV");
        country.setCountryCode1("RKG");
        country.setCapitalLatitude(8);
        country.setCurrencyCode("AUG");
        country.setCapitalLongitude(9);
        country.setIsoNumeric(298);
        country.setCountryFlag("vh9TwRUX0rXva1h2kzKFwqwkgqEqKTrQCPvWEeJ5MnakpWXG11");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(11);
        state.setStateFlag("gqY46e4H33OGOkjAC6yE4Okbtsdfg8Z07NZrHdDARSpPxZ44d9");
        state.setStateCapital("vupX8qcVSJinXjGCnGNlznLRp5Ky39aJsuIbh711Gz30N5Wvnf");
        state.setStateCapitalLongitude(4);
        state.setStateCode(1);
        state.setStateDescription("Pp0LE2aaZJru1jWuUtTUnl7buxltfElGEOlBa5UjRJzMuwb6mJ");
        state.setStateName("LqwMjZCqMDxbD55iTstfZPS9hstLF9BNJdGGrltBJsDfKHchjF");
        state.setStateCodeChar3("xocB6vqHoHRsQu1bntrJiMiiYreFUl8k");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("nXl6YurA9IE6R0Xr4KNwtKBdecjcgx5s");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("EfIHy8FDtIe6sxSvF5KvjV10a7ZA4fN76ELJsCVtRgXBII7pg7");
        addresstype.setAddressTypeDesc("VtqWVSXyvkx5LDwjQS4GQMyBbBluDBMyEuoCYBDlrElbfBREd2");
        addresstype.setAddressType("W2WzbrctZIZqt3M9hwkL3b4nkaa6HEa2EbHWsf83aCZmiqRvt5");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(11);
        city.setCityCodeChar2("Bqli2bbokIipQhgUW8kwGVU6CiuQqegU");
        city.setCityCode(1);
        city.setCityDescription("shPQWjLBBjV9Bx0sFmaYFJAXqrUf05KFPQnSiUJkLWeFdkJuAw");
        city.setCityLongitude(11);
        city.setCityCodeChar2("nCwGvQE9ojspPAsIOfDuWdkRHu6MnNqm");
        city.setCityCode(3);
        city.setCityDescription("6VBg9UTRFDiekcMSbReUcF31ueqAOB6jaz74MJbk9yTjSf0e2s");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("woZX0qFgBwznSU1dh6KonmMtttLFmRhLi1GlWQcxMc2kU45Ycm");
        city.setCityLatitude(3);
        city.setCityName("CJEeJMB5xFMNbegw4WlczbZIS8Kss6IIdeKN5cpmZJcsn3bm3L");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("LHLM5F2zLUe2N5YlE55qltGnWDrg1rCQa7gmMUkscKQYsJcJJ3");
        address.setAddress2("QdpXbyVot8RXoEMTEHPdC0zH78jgGmJzxRFC16IueBoW5jNQCU");
        address.setAddress3("nQFPRPQEr6stXWRals7sph7witl0gfIQIfr9rjays5uljHpZ5N");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("qExDyCQDEwu");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("CPy9LQ");
        address.setAddress1("McOr8kbOAPOnOFJDBZw8JF0U3z5ojY6h49CMSYvaXAAtAakKzD");
        address.setLatitude("1sppaEw8VruJMxjtZFYnAksG6CXlOv3PGxCgAWqSxaJMiCiWgL");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("0aswlBFTJ1");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("UMUDlV68COH3QtN2TFf04aENG5YvvTMbqIn20UW1AI91jomNX2");
        communicationgroup.setCommGroupName("nGw62iMvScCk20P4UAvnAhFv75bOXqVSC290UVTkxJfD9Lesyh");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("2Uq491L2XOGLAo8t0SigAwGI8Ld5kLQveWDVzBIvAWcNoHY4pM");
        communicationtype.setCommTypeDescription("jbaEPxtyJc6mGzynLtoFvVZgjUrkj0u82rPzT9GUZyuUfL2NXa");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("RQUIRiXi8jP0nSFYM5jXNSCy7hukQ2vvD9kNIpvWumfbeUXFxv");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("zugF8B6QBU");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeFirstName("N25BwWvHXI1ZlcRKgQf8T2b8EKQa36iAy7S1bjicBibVFsCeY0");
            corecontacts.setNativeTitle("QNB1hOUid66ovSbc2mVRwIZcye1NI5V3nniXq0oyniKjmBrvqD");
            corecontacts.setVersionId(1);
            corecontacts.setFirstName("eyzHxNYzRcUZg0cSeoYU15K8rsgsF91EKUASkmBOBdVneyWWPk");
            corecontacts.setMiddleName("jHnEKs2EVmhLNR99h1j6LfusxENMxysP4oxdVrRPmBLkb9mvrx");
            corecontacts.setLastName("BGlfFHbCdfXOvLDfY5KrY7adOG7x0izbqHTKXgVAwoy6apbP4Y");
            corecontacts.setNativeLastName("LaQ2dJPkvlXZhDnqw1FVbLlWGN9tUJSdA330EsZep732CYx6ru");
            corecontacts.setNativeMiddleName("3gT3SOk6nO4V14ER3t1mSOgI1cKOaWFFNfBrRjNJGuLfAwCqe1");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465991064689l));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465991064696l));
            corecontacts.setPhoneNumber("lsRDOeuT9RbDgBnyA5jK");
            corecontacts.setAge(37);
            corecontacts.setEmailId("TK4FpQaKayA30V9hLXy57rjCI90ngJR47scQohF3Vj04m3Icta");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "Q5cuKzhpRwwL6BwU4f1qfUjSZo6nJfYeLLL2J9hjPwvptOQ6wN7M76QEFgMDjZb3y0uLhYsWGZsd63e6Tp8Xw8RpErmhLjyJRrMf6B6cWQSu8pZ4pfc47zUh2VjptYLVKYsGvexcVrdXwOrNulawbuUfFlydTKKvHJNtdYgQsJ4eyjyaF9Mj26qYs1nruTBIdOCeWHbg9YWWOd69IixEJMMWhrA4YqSOCvz4ZpkzwTS45V9Wj3czYESbgPZbvpDDe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "qZt7tkcc3tRlSiCQfxgcBRXsBJetqzgivZUC8Z5hSHisv5BKdrMIvxtfbzvvogWGmeYpZvs1gjYW72fnnGCCPM0ijXaYTw7gPJXFRg8L1IaNZrrDxmCtxaQVptj74OD7PwcdjCpfBTAbyFyfnzvuEXhW5c8WzmhfTw17UDYHVtCWXGiF4jQKALUUBQYUONNqLq56s3hUK78UR7hF0tXfox045qLkuhWbKTi1bLiaoj2ZY38yKJfgIZzHXRYadWs2M"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "GZKcnO9tYCFxNvQiOKcPPZgtLKZtynT0OLirZ9JXv9uy6nlBNjzSXAegNlWF0OykHG2Bzk73GrmSujWJRpukfxaKQMUSuji9oRAcViN9vz1R0aLivJGQMVekJSJgNdHTwEHlC75YzKbwozEqrW9i6quGBXzPZ3L43tiEUNaeMYbVuLO7kl2YRsvt9lQ4OjtEdtpU92Wg1ofLkJ7Zfxc7bDTTSfdrDTnvhZRo5xyYTpHiV2PmpPwpg4F2CmaUgvHtV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "gNPrBm0NtIblJ5IB1WyYy3i48lMlaYUIyegBI8eHI5lOC77TncrILPC1dhB4Z6Ezv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "eqEc2kaTlzCKB9YQOqGlkp1QnORmVnyQmcrdEggsasdQdI7QfgmLOsNyIvkNooyKj3iMtEJanmgtccXQxPQorWPVUrrsBDvSRg18rizA5pRXOO3jOP2OVVbg461ia9rA2qV3pWfmO0rsBmR55L5bjrBqrFCKLo3Mf4wtT2dX7bNOwnk399zqDc3fVV4WpJyDwsitDTRbziitlhTH7bg1zXIEhXiR3uK4ZPBoiHA18kXWAnxHIQ8kipwH8iB5SIMVI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "nryYlhErFuZ4NBQIdFU8yEGHrZ6WqtAF3eHNhO4XyAhdPhHRWYiLHNXih1JgEiodthjhchfafXSnRCBa1Di3uYuCtZOl0iKewCVLo2ivbXmXeo0eP8GdFaxjUHE1QUsyxBcbIwo1wq2vhJSgY7D69pDc6gtuGOt8R0StDCtPtmwFeJTJc431AaPGOWbeqPGnEnFGFS2NnqzFTgpF8cpIjsRdYSlLICbpah2EeaAavOyqbXIlaThlcqP7xLwgk6I9u"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "4Oj6Y1b88f8JKpQJ3Roz9PsbaimgQuaXOPBLVROJiwNDAGg1MaqqMNuvih7fCIFZJhhJaZufDtg6q0hlkyeBDtfR9ITkv64A7aQaZKKqjBZFYKGxv8XLtYliUhiXGxxvbH0q02gkmw0CXkhQ3pbGziu4fYytw0wDNHThEIzS4KkcViyRv9knfk0cWuMCWDw7Dmf0nicWbrlzdfhNLUYudXdJdo2HHsdHD6UuqLfWTf76vIQtRY79wZNyRGN2F6pwv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 229));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "Wrl0c17A0zZd6vMK0ql2LkSMkdWJO3MAowot1ZD5z3BAHWnkDwAE25qP1Qr22YIv8VJaXQV2g5uGtVLlP11bumjpiMhy1ZaoxZyOQsMZFsCLBl7fITsRvZfJ7VAlF7Bo4oWcAV5NJIpQSIU4mmAviDN0U8mIfe4KlkdjY9Iqllhwu0huQefV4BP0vAX3WbeosE6Wl1xLZ4gLzKEfpGtDtlcEjx9RUz8TG03YzLDdlz2d7FCkdyI8JCmA8rK37KDS8"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "YmhnRFGzy3G89b1nSeTb9"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
