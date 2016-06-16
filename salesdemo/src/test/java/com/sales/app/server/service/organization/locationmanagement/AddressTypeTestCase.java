package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.sales.app.shared.organization.locationmanagement.AddressType;
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
public class AddressTypeTestCase extends EntityTestCriteria {

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

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

    private AddressType createAddressType(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("mWzDD7WWJjYgI5g8LJS9hlT9N9UXurmzhRyMmNvrMLaVxixyf2");
        addresstype.setAddressTypeDesc("FaDyyGb6u1NO5EOxemKMPYpmBkjiZ08Xck2LoadDYAydpYdiPX");
        addresstype.setAddressType("JN0ACDvztxew4a7OotKInO5jNPx8penrlDrZJDLBKdlCmztGN6");
        addresstype.setEntityValidator(entityValidator);
        return addresstype;
    }

    @Test
    public void test1Save() {
        try {
            AddressType addresstype = createAddressType(true);
            addresstype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            addresstype.isValid();
            addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            AddressType addresstype = addresstypeRepository.findById((java.lang.String) map.get("AddressTypePrimaryKey"));
            addresstype.setAddressTypeIcon("ke6kNV7faHkYM4Qx4ZmSJWmyCWnGEXkNFj3xLvTU7WiI8ceiGE");
            addresstype.setAddressTypeDesc("NyNpsHZwzkmMyXtN4LYP3meaTah6WOe8Q7jtuvbIImtijOR3l2");
            addresstype.setVersionId(1);
            addresstype.setAddressType("0mXc5hHGjcmw1PT8z8AwOR9RVqtL8BHphTqbzIgyihDfKpwFjL");
            addresstype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addresstypeRepository.update(addresstype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            addresstypeRepository.findById((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddressType(EntityTestCriteria contraints, AddressType addresstype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addresstypeRepository.save(addresstype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "addressType", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "addressType", "ozyXyDMUODVyI9TfzNYnWTUkSLqXvsdmk24AiDf7ctu9mjeT0PdWjWDDV6Mp6kfxq2FozWqcnUO80HgrQ8wEFyF1l9TbbDnTziTj3kC6q4uht13qFyAPkwIsEUGqFBRxg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "addressTypeDesc", "zicD7NKUr9XX7h58oFKYtnk15CyYKr9KmHNOWh0TEnGW46tAfeeIewTTQqq6LBTLL2e6zVxXE1w0ndt3Rw7gkW9bJIqwb3DdsFlFQzowSfVkrZgTzsf8RJjUwHmvXPZ1qZ0Sdcu2ueVrTiQ7ONTh1wpkb1aNlYD7XSkhYdcd57aZR7W4FidMpfGrCdvmYKWyPldmk2gmkYminphWYObxlatppPfQLycHEufOF6NrTzo1hiisJkLcDTLhka7KYW4UX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "addressTypeIcon", "vMEh0LzPtwXt7WRtrNRcKzxdzVDFD3AnNkMT06h1IOQFaPNwJNrabhQZJZTLbQPb6iqa0yj4YH89nK4K1WNDmuXSusSdISmpckTITvOQKYvg2kvDTixSxXQ0gUb4W2hgU"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AddressType addresstype = createAddressType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = addresstype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(addresstype, null);
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 2:
                        addresstype.setAddressType(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 3:
                        addresstype.setAddressTypeDesc(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 4:
                        addresstype.setAddressTypeIcon(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
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
