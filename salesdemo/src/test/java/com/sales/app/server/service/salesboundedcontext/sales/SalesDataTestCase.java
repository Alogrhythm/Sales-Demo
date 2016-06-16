package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.salesboundedcontext.sales.SalesDataRepository;
import com.sales.app.shared.salesboundedcontext.sales.SalesData;
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
import com.sales.app.shared.salesboundedcontext.sales.Material;
import com.sales.app.server.repository.salesboundedcontext.sales.MaterialRepository;
import com.sales.app.shared.salesboundedcontext.sales.Brand;
import com.sales.app.server.repository.salesboundedcontext.sales.BrandRepository;
import com.sales.app.shared.salesboundedcontext.sales.Category;
import com.sales.app.server.repository.salesboundedcontext.sales.CategoryRepository;
import com.sales.app.shared.salesboundedcontext.sales.Retailer;
import com.sales.app.server.repository.salesboundedcontext.sales.RetailerRepository;
import com.sales.app.shared.salesboundedcontext.sales.Distributor;
import com.sales.app.server.repository.salesboundedcontext.sales.DistributorRepository;
import com.sales.app.shared.salesboundedcontext.sales.SalesRegion;
import com.sales.app.server.repository.salesboundedcontext.sales.SalesRegionRepository;
import com.sales.app.shared.salesboundedcontext.sales.Channel;
import com.sales.app.server.repository.salesboundedcontext.sales.ChannelRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.sales.app.config.JPAConfig.class, com.sales.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SalesDataTestCase extends EntityTestCriteria {

    @Autowired
    private SalesDataRepository<SalesData> salesdataRepository;

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

    private SalesData createSalesData(Boolean isSave) throws Exception {
        Material material = new Material();
        Brand brand = new Brand();
        brand.setBranddesc("dGpk08fkCYkbMotkhESCjm6cmHfxCsOUpgiy7ZC2vpI0CxLFsZ");
        Category category = new Category();
        category.setCategoryInformation("SUjycrVqD3uSUKaLTTccVgcDvspvAErU5050osrv5h4ugSCq48");
        Category CategoryTest = new Category();
        if (isSave) {
            CategoryTest = categoryRepository.save(category);
            map.put("CategoryPrimaryKey", category._getPrimarykey());
        }
        brand.setBranddesc("UYECw0kGL2M6dxKNV9JHFHz5BhQEOC95PtrtpXdZwbCaLLMGQ3");
        brand.setCategoryId((java.lang.String) CategoryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Brand BrandTest = new Brand();
        if (isSave) {
            BrandTest = brandRepository.save(brand);
            map.put("BrandPrimaryKey", brand._getPrimarykey());
        }
        material.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        material.setMaterialdesc("DNq3seMF5UGZ5ARlZz1wNkHLsHTBnKuKChVa04uoaeOzGphJ2b");
        Material MaterialTest = new Material();
        if (isSave) {
            MaterialTest = materialRepository.save(material);
            map.put("MaterialPrimaryKey", material._getPrimarykey());
        }
        Retailer retailer = new Retailer();
        Distributor distributor = new Distributor();
        distributor.setLattitude(4600.0d);
        distributor.setLongitude(3500.0d);
        SalesRegion salesregion = new SalesRegion();
        salesregion.setRegionname("5jipsyrRKqfTMwMOQwVHDVVZxmYRNwQkUcnbwyJq49O2pUXCja");
        SalesRegion SalesRegionTest = new SalesRegion();
        if (isSave) {
            SalesRegionTest = salesregionRepository.save(salesregion);
            map.put("SalesRegionPrimaryKey", salesregion._getPrimarykey());
        }
        distributor.setLattitude(2290.0d);
        distributor.setLongitude(-8800.0d);
        distributor.setRegioncode((java.lang.String) SalesRegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        distributor.setDistributorname("aDel72QC7P72P9RDikgZ3BENM43POJq56D2emLW3JNFI8lJGOX");
        Distributor DistributorTest = new Distributor();
        if (isSave) {
            DistributorTest = distributorRepository.save(distributor);
            map.put("DistributorPrimaryKey", distributor._getPrimarykey());
        }
        retailer.setDistributorcode((java.lang.String) DistributorTest._getPrimarykey()); /* ******Adding refrenced table data */
        retailer.setRetailername("9PrJADlbW88svps9kBqlCNEIcVUlRWaA0IXsVhvdN2D4Nt1E0E");
        Retailer RetailerTest = new Retailer();
        if (isSave) {
            RetailerTest = retailerRepository.save(retailer);
            map.put("RetailerPrimaryKey", retailer._getPrimarykey());
        }
        Channel channel = new Channel();
        channel.setChannelInformation("c75lQRYd3pMrot10gTMeOSQD3aCyHhvOkn2ZKKi2USlaQ6Whd1");
        Channel ChannelTest = new Channel();
        if (isSave) {
            ChannelTest = channelRepository.save(channel);
            map.put("ChannelPrimaryKey", channel._getPrimarykey());
        }
        SalesData salesdata = new SalesData();
        salesdata.setSalesdate(new java.sql.Date(123456789));
        salesdata.setSalesqty(-784.0d);
        salesdata.setNetsalesamt(9000.0d);
        salesdata.setMaterialcode((java.lang.String) MaterialTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setRetailername("wDXxnKyUbxN9BzPS23CvkgfE85QZAUt89RUYBpv276W6GjxRf0");
        salesdata.setSalesyear(2147483647);
        salesdata.setCategoryId((java.lang.String) CategoryTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setSalesinvoicenbr("VDRFlsMYM5GmXKQHCvyEAqGN5FmtoAGrlQ911WPCS36H8I0qxw");
        salesdata.setBranddescription("n");
        salesdata.setMaterialdescription("36Zeip7LwyuLgXm1f35zL02e0Rlw6oLAsquKHV4PKEU1rVA8h3");
        salesdata.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setReatilercode((java.lang.String) RetailerTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setSalesmonth(2147483647);
        salesdata.setChannelId((java.lang.String) ChannelTest._getPrimarykey());
        salesdata.setGrosssalesamt(-2220.0d);
        salesdata.setEntityValidator(entityValidator);
        return salesdata;
    }

    @Test
    public void test1Save() {
        try {
            SalesData salesdata = createSalesData(true);
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            salesdata.isValid();
            salesdataRepository.save(salesdata);
            map.put("SalesDataPrimaryKey", salesdata._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private MaterialRepository<Material> materialRepository;

    @Autowired
    private BrandRepository<Brand> brandRepository;

    @Autowired
    private CategoryRepository<Category> categoryRepository;

    @Autowired
    private RetailerRepository<Retailer> retailerRepository;

    @Autowired
    private DistributorRepository<Distributor> distributorRepository;

    @Autowired
    private SalesRegionRepository<SalesRegion> salesregionRepository;

    @Autowired
    private ChannelRepository<Channel> channelRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            SalesData salesdata = salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
            salesdata.setVersionId(1);
            salesdata.setSalesdate(new java.sql.Date(123456789));
            salesdata.setSalesqty(-9700.0d);
            salesdata.setNetsalesamt(8000.0d);
            salesdata.setRetailername("unHEMCFebXShQm7aUPEsGfgHluSV3uKS54Xi9wc3DdNkrZiLup");
            salesdata.setSalesyear(2147483647);
            salesdata.setSalesinvoicenbr("M244tu0tZJE7UhRBKBiimM7uXssO1hvnSnmWlc7M2HRpQ77jeL");
            salesdata.setBranddescription("u");
            salesdata.setMaterialdescription("pUtkdcdgWXukcgZwtHVm8R763C1OjsafcTZdaewijrjDio0EcY");
            salesdata.setSalesmonth(2147483647);
            salesdata.setGrosssalesamt(5820.0d);
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            salesdataRepository.update(salesdata);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBymaterialcode() {
        try {
            java.util.List<SalesData> listofmaterialcode = salesdataRepository.findByMaterialcode((java.lang.String) map.get("MaterialPrimaryKey"));
            if (listofmaterialcode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycategoryId() {
        try {
            java.util.List<SalesData> listofcategoryId = salesdataRepository.findByCategoryId((java.lang.String) map.get("CategoryPrimaryKey"));
            if (listofcategoryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBybrandcode() {
        try {
            java.util.List<SalesData> listofbrandcode = salesdataRepository.findByBrandcode((java.lang.String) map.get("BrandPrimaryKey"));
            if (listofbrandcode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByreatilercode() {
        try {
            java.util.List<SalesData> listofreatilercode = salesdataRepository.findByReatilercode((java.lang.String) map.get("RetailerPrimaryKey"));
            if (listofreatilercode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBychannelId() {
        try {
            java.util.List<SalesData> listofchannelId = salesdataRepository.findByChannelId((java.lang.String) map.get("ChannelPrimaryKey"));
            if (listofchannelId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.delete((java.lang.Integer) map.get("SalesDataPrimaryKey")); /* Deleting refrenced data */
            channelRepository.delete((java.lang.String) map.get("ChannelPrimaryKey")); /* Deleting refrenced data */
            retailerRepository.delete((java.lang.String) map.get("RetailerPrimaryKey")); /* Deleting refrenced data */
            distributorRepository.delete((java.lang.String) map.get("DistributorPrimaryKey")); /* Deleting refrenced data */
            salesregionRepository.delete((java.lang.String) map.get("SalesRegionPrimaryKey")); /* Deleting refrenced data */
            materialRepository.delete((java.lang.String) map.get("MaterialPrimaryKey")); /* Deleting refrenced data */
            brandRepository.delete((java.lang.String) map.get("BrandPrimaryKey")); /* Deleting refrenced data */
            categoryRepository.delete((java.lang.String) map.get("CategoryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateSalesData(EntityTestCriteria contraints, SalesData salesdata) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            salesdataRepository.save(salesdata);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "retailername", "eahiVdih1l1ZIdTJ7v1makEVWrrq6SIco5jeVqBjxxsBFYRKhfa4W64lIDxt0sf6p"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "salesdate", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "salesmonth", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "salesyear", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "salesinvoicenbr", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "salesinvoicenbr", "gJ6hMeEzV548YJrqyFc0ie6mSgacC6mh1wIYy4PMu4SY9jhJ37rO1jOoiUWOk85cu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "materialdescription", "8sggFzqowWVrfKsvY2klb8YaHGlm6QMi4U551TPPHfpjkrq6g3Jhf16MqpXURbZhW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "branddescription", "Red"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 9, "salesqty", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "salesqty", 1.0361391796139192E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "netsalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "netsalesamt", 1.00070513037501E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "grosssalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "grosssalesamt", 1.0553189140350722E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                SalesData salesdata = createSalesData(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = salesdata.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        salesdata.setRetailername(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 6:
                        salesdata.setSalesinvoicenbr(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 7:
                        salesdata.setMaterialdescription(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 8:
                        salesdata.setBranddescription(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 9:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 10:
                        salesdata.setSalesqty(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 12:
                        salesdata.setNetsalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 14:
                        salesdata.setGrosssalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
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
