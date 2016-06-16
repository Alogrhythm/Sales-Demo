package com.sales.app.server.service.appbasicsetup.usermanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.Login;
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
import com.sales.app.shared.organization.contactmanagement.CoreContacts;
import com.sales.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("1JhfwYuVl3k0O9gMJhbr6aUFxSakjYitHlpMWTto59kUSzNyEz");
        corecontacts.setNativeTitle("I5T22NHsMgJeaJJbaqLNB9MvFuDHEmIg2lM1FrhUaFt1Y59dSm");
        Language language = new Language();
        language.setAlpha2("b3");
        language.setLanguageType("3H3J8nyyxtaRQB4dPjbx5ENwIY5If2zv");
        language.setAlpha4parentid(9);
        language.setAlpha3("FUO");
        language.setAlpha4("KSBK");
        language.setLanguageDescription("BxgMyRM5eNgMqA0OifFmGWrSYIZ7dmho3ILnVRF3kDpkyiUvCe");
        language.setLanguage("cqoOYrNXMciDDfy3j2cYTZ8aoBCzko7xxSMJtdqmjx01kD9YZ3");
        language.setLanguageIcon("8qce3B8BEVHe5I5OG1W41ydWWKciCKTkeJTzDYZypinwDoDSiZ");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("LrcUFBiIvJWQyG7kbvicCpWAUYCEYBMJvFBzuR22o0u2qWWBO3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("xPLlSeJq4ZoXUmV5tKAD1bE4ljOV8Ox1aCj5pLMPEzSIak3rHx");
        timezone.setGmtLabel("RsSXK1uvsXDTTceNISC1IY1dlFLMWpKmzsir448rNxoZGF7SXa");
        timezone.setCountry("TWAUnByJQpKHsE7EyZhCbNiWJG7L7JUsBK8iiRIqRIataUHdKr");
        timezone.setUtcdifference(3);
        timezone.setCities("uYqQXVSNhPGiWciwr6L8PdG8GHTw0GUj8VZgM7ykZuX41gnhXy");
        Title title = new Title();
        title.setTitles("HXq1aTbXL0hScsri2QY7Mrkba9PoKKShR0dWl19atR3C622txZ");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeFirstName("o6WMoCGEv7x6wNOqUSZIViFijzdN4m70zvSPzurtgFu3DVEpVT");
        corecontacts.setNativeTitle("aS8je63GypzxiVRN7O8DzDi0duVglFp4LrIprohDmBbAtxuKS7");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("pcbg9gZjhBNC4HWZITj8pjDo6XhIsEzTDSbL8DIB7mJH6ojyQG");
        corecontacts.setMiddleName("MwlgNqRYQlvWmgybm8PV2R759xisZ5pWnfcJoMIyNvMNxLPK3D");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setLastName("nSj8NDXaF6GttwaqyJ23i1iqCJ5C5BHVmqpHoQ7Wk8hrMPiVue");
        corecontacts.setNativeLastName("NWdPDy5TxpEFt1OAilXOpFRwGPIhkVaD2kVBFI01JB9e6e9KGV");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("xZ6RYEvRv3cSlOqOD31aa6blCvl1tCKwWfm28JPIPnBPdHisHI");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465991076454l));
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465991076454l));
        corecontacts.setPhoneNumber("G5HZw8cpPjxC68grvQMw");
        corecontacts.setAge(48);
        corecontacts.setEmailId("TeoY74j3tSYoTqwSuXYZ36oVkg3uX1ywYw9bBPJ7rU1UrvRsbL");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCapitalLatitude(5);
        state.setStateFlag("ivdFtC4AAbsiW8wT6ek9honBRbFTat7HEzTAxc9qyZRha3BmZh");
        state.setStateCapital("WXUu8rmUuAFFqhjKy1y6Rl6gFqWcb6Nv1c6opkl97eQcs9oDNF");
        state.setStateCapitalLongitude(6);
        state.setStateCode(2);
        state.setStateDescription("a9F93JG7GM29Sj1o6iRMOVw0bh4LM8iCFdraCUuT2uGUgnh3ai");
        state.setStateName("KAuOT66JdUQ4hQF3cEQxEZsqyeqYmDU6e57MpIbmIqzfjdGI1v");
        state.setStateCodeChar3("qagYzXyXCMUtAsBaW0Int8ndC5IK4HSH");
        Country country = new Country();
        country.setCurrencySymbol("GGt464MUBLk5bWJRIoYk5JHBlXP0ppSB");
        country.setCountryCode2("puk");
        country.setCapital("UimNXFzuLMp0hc7U1PmCt0rPNaoJs2BW");
        country.setCurrencyName("p87Djq8cLOqcmurttp17NA78kobPEulJjZstqB9I9bMyaubESl");
        country.setCountryName("9mFPaTkBdON2Y0NXXyT0olR5EYHRck7lsCm0mWevZsWwj4HUQU");
        country.setCountryCode1("k8V");
        country.setCapitalLatitude(10);
        country.setCurrencyCode("n4I");
        country.setCapitalLongitude(4);
        country.setIsoNumeric(377);
        country.setCountryFlag("JJGyQRA5rNNKTtkUR7ajYb38mWwJ2q6aSe5QWzpVYu9ib6aCWt");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(3);
        state.setStateFlag("9KoG2mjoZ7Jbg6Kr3T87XJQIS2zuVvammZPKTDySkiWZATYt6z");
        state.setStateCapital("luvqHEAJyjd85o22g3Hd1BK1oYL2OPM3ZDCyzU4eRVGHjVw4z5");
        state.setStateCapitalLongitude(7);
        state.setStateCode(2);
        state.setStateDescription("EmkK2NWOr3eI7TuEYP9MMpBUiSFppk8iPMiQbpjdjWEqBkxrRZ");
        state.setStateName("udbKAKk9iMtxpIEfU9myW4na8fJucS2Tco37NakAtCTTLrYtT6");
        state.setStateCodeChar3("CJVtz72DEAHCv9V9v9dgNTkeA3RAZPju");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("hVdfdtKeusFfHiSH8Ao4B9rqI5OAgjpy");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("mjq1rTKirnCsEkIDx5oLG3ZZZSBiFJdTQNxMlA9wCVDS1jAmly");
        addresstype.setAddressTypeDesc("t3SP95fCXsXHhtYQfYZUqIvslY4nliX5QEvVXJS36i8nFyRvZd");
        addresstype.setAddressType("8VymiLucACO54nPK8jSWJGdlp8sOb7NeROiVk7SEFdRZecVget");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(8);
        city.setCityCodeChar2("HWB7yUy6Fj6rZagHAW6cS2w4Pwhjlsvz");
        city.setCityCode(1);
        city.setCityDescription("lCBBvVIdw1NFlRxriWmS5do3NXSoBcKrO4YkWZOH694ZPGjfya");
        city.setCityLongitude(6);
        city.setCityCodeChar2("wgqzCRpoVjvpKlB6OzTvBSf2Y79MbhYa");
        city.setCityCode(2);
        city.setCityDescription("qLzlANkjBySyAHOmoCAcqfrkP7cWxEFkbyU1jbOKRCw93NZKZB");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("Xd9p6rQ0DnrvE4aDXcpTGDUjp3WYWe7x7flKc3lgqGn4Q64anj");
        city.setCityLatitude(5);
        city.setCityName("4EU4Q9zvQufxQxxTPOksMAoaMyNDIaJ98eqFmjwRtN3kvz9EhL");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("NwPzhwIRCBsZyVxsbgE79IHUdYwBUd0fJepD0JlS9tZC5SzlhW");
        address.setAddress2("fe5bPUMEFgjhiW1PnotY3CNmf7QUeMzHoLbHZEGp3GkARgCtjt");
        address.setAddress3("RDmSCR7hei71InFr32t0i3zlCYIQAvKiKX46RQKHgQPZnFr31a");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("ayZPEIMd4wh");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("Cgetyu");
        address.setAddress1("EssUsqjUtUUMFCIkFetu3CjC7WSADcXi7I73CNqsbbM6WuUHgu");
        address.setLatitude("1nTnfj1lOAEf1nOAi1KmSM4Newu0Fjqbwe3Qvh8LTNgsuY8KLQ");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("DJTWak3NH9");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("EXwNANaBpXSUQlihKfvio0h3UOJlIHUkiuf5rAGqkVnzOo69sM");
        communicationgroup.setCommGroupName("xib55lbHyVgkTuUHPUiIxHhxgAqtQWDgp1LzN8HeK2y5U4FA1E");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("3lzkLWCLTxLxVQLSIK8k6ExWJaOdpaFQiTgeuaPQRBGaIm4BXI");
        communicationtype.setCommTypeDescription("zzbsmLat3RQubVYsazj8Qqyw7najTd6Py9QrZDxpxOkAkAPuqy");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("rFCUJoLcNibNW19SlSQZA0dPltrTbmD7U6b6ESU5t2IhgTtZwa");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("THVMGPjIcC");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setIsLocked(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("PUpigtjDkHAZBqc4dY9NQolhap0afXb1Cce1qII891QnNMpcbf");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("UbrsIiWSgWxBi3dIN7XLuxsKur6BQZ3ZTNSn6HA4Jw44YHWwXr");
        useraccesslevel.setLevelHelp("Xp5tLdwC05vwmLms2V7IZYIEMauI1iLkyB5VYNHQRRCpcr09X3");
        useraccesslevel.setLevelName("2a4lpP849jZxKu8GRl6MHz0PtaLj4xhpF05qoVI3TdHKFfteHq");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("Q7x9jbtmS8J3LjldNH62ExnAmgnoKVEEE1fGSYYGPdjhj03WHZ");
        useraccessdomain.setDomainDescription("chJ64lQixnEkpomGOibgdujjPQ32TVNHJwE08bt6WUe5e0BTsk");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("W0qwRrYMIZxTbtA0LVCjMVbrGMBs0ZQCTWYofhTlD6LlM29Yce");
        useraccessdomain.setDomainHelp("aOhSX0zK8RV28yFAXBK6OOidbG1fxAkWCqdTFXE7TrHFJcIITq");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(54377);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(2346);
        user.setIsDeleted(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465991077007l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465991077007l));
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordAlgo("0LtK5g15oBw2p2KmrJU8QJ2obd2QYG6H2yPvrVHPuwddyk8rdq");
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("E889txBTsliz3ya0OkiXDxnXix14SuAPwlrd89dry1Ogx4FSMf");
        Question question = new Question();
        question.setQuestionDetails("h0nbg6L10Z");
        question.setQuestion("VRFEHmNlcsE9IQESs4vJMsfg8GC1mVW01SzfcYiUz0hnEmOOsN");
        question.setLevelid(5);
        question.setQuestionIcon("H9kZXmsU2BG8JHIX5TYFyIVqln8HCacZEvVkW2GjmZgKBqEr90");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("WQn5vLIwQwEMi9O9q7b84DzUQ1EDIshUnmZ2gCnKy7MTxyn1g6");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("zxiTWCr1d9e5kqf3TNWBi8DRKfzIeJ4AfQcFKKOt7SiqoL1Fca");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465991077173l));
        userdata.setPassword("RlgOlJap4SXdqHiKb9sA5XqqLJiQxjTjCrR2r4CVUBuYwvedPE");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465991077192l));
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(8);
        userdata.setOneTimePassword("eS5N5EvHIVoBnzJ1AjdV2W6rThwlMWQo");
        userdata.setLast5Passwords("zo3WF31JmX9K0YrGbJqCb16ZcqVPk3EgYI8YSw7a9TTN2QixJq");
        user.setUserData(userdata);
        Login login = new Login();
        login.setLoginId("8qu2jL8zOiUmjQO0Ot2PxqY4j2x4AGQM089qEsDxmK0s4YMOo9");
        login.setFailedLoginAttempts(5);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("LXaKferfeslQxmgA4T7sAjOWC0Vdm1NV");
        login.setServerAuthText("wDQva1FbAL3Bbwtu");
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setLoginId("mq70lrhcV50l7D7gzBOpG1PB13MOY0WBrl0kVNyYdgdsD6gtj5");
            login.setFailedLoginAttempts(5);
            login.setServerAuthImage("c4g0jAMUy0HiFma7NU1rPzOEPQ0Cwml2");
            login.setServerAuthText("6hbWa1QpQeZCDzhv");
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "bItD3pB2Yy6ygTTuIDpUePIYdNELGKY9y4LmuMrxOKTe0N4oHXexqCGVqBACCWWSYY9qnyVlti5TRuIgYkCC4bvmTm3CAWovwg3EVwiCZ4kWSH0ItgVcqnFB4vUWnDKdSzD5w4xNrveG9bcImV1vEcfYsOnSTzmzw6z2cAJuBtyGgvlS27SwyNYov3fxZ5t99Rx2Apl7j"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "K2HCPinvdGKO3LfX38yx450u7Bz9qKEAN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "jVqOi7OJjNLcy9D0P"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
