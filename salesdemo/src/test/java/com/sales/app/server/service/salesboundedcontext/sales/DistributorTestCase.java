package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.salesboundedcontext.sales.DistributorRepository;
import com.sales.app.shared.salesboundedcontext.sales.Distributor;
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
import com.sales.app.shared.salesboundedcontext.sales.SalesRegion;
import com.sales.app.server.repository.salesboundedcontext.sales.SalesRegionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class DistributorTestCase extends EntityTestCriteria {

    @Autowired
    private DistributorRepository<Distributor> distributorRepository;

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

    private Distributor createDistributor(Boolean isSave) throws Exception {
        SalesRegion salesregion = new SalesRegion();
        salesregion.setRegionname("l2MuwuSgmO3JkNq8jCSIAyuNEelb8atopwq1p4j5ZdD4vCm4pV");
        SalesRegion SalesRegionTest = new SalesRegion();
        if (isSave) {
            SalesRegionTest = salesregionRepository.save(salesregion);
            map.put("SalesRegionPrimaryKey", salesregion._getPrimarykey());
        }
        Distributor distributor = new Distributor();
        distributor.setLattitude(-3100.0d);
        distributor.setLongitude(-1300.0d);
        distributor.setRegioncode((java.lang.String) SalesRegionTest._getPrimarykey());
        distributor.setDistributorname("a9PPpjZM7lDE2TAaGaMMcBsDHcsHk5YJNu5k9hwJjzwg7AFHun");
        distributor.setEntityValidator(entityValidator);
        return distributor;
    }

    @Test
    public void test1Save() {
        try {
            Distributor distributor = createDistributor(true);
            distributor.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            distributor.isValid();
            distributorRepository.save(distributor);
            map.put("DistributorPrimaryKey", distributor._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private SalesRegionRepository<SalesRegion> salesregionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            Distributor distributor = distributorRepository.findById((java.lang.String) map.get("DistributorPrimaryKey"));
            distributor.setLattitude(-9680.0d);
            distributor.setLongitude(-6700.0d);
            distributor.setDistributorname("vT39XMKScByebYymN8kJgoo8TXtrbpwOKfmAwrbK1tBXbnb6lG");
            distributor.setVersionId(1);
            distributor.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            distributorRepository.update(distributor);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregioncode() {
        try {
            java.util.List<Distributor> listofregioncode = distributorRepository.findByRegioncode((java.lang.String) map.get("SalesRegionPrimaryKey"));
            if (listofregioncode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            distributorRepository.findById((java.lang.String) map.get("DistributorPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            distributorRepository.delete((java.lang.String) map.get("DistributorPrimaryKey")); /* Deleting refrenced data */
            salesregionRepository.delete((java.lang.String) map.get("SalesRegionPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDistributor(EntityTestCriteria contraints, Distributor distributor) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            distributor.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            distributor.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            distributor.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            distributorRepository.save(distributor);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "distributorname", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "distributorname", "qWfTynSky81HF3KhlN70Pb22MmuEnGUzyIbPiWRNnqlz4QqR1U2OTWOJJIw6B5sqq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "longitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "longitude", 1.1765492756671818E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "lattitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "lattitude", 1.3872893177465688E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Distributor distributor = createDistributor(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = distributor.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 2:
                        distributor.setDistributorname(contraints.getNegativeValue().toString());
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 4:
                        distributor.setLongitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 6:
                        distributor.setLattitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateDistributor(contraints, distributor);
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
