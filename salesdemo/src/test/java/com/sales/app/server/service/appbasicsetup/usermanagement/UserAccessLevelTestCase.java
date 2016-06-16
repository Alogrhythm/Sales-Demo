package com.sales.app.server.service.appbasicsetup.usermanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("PT6eiNy7Ersnh6DA8sSFfN03LK80H1H1jFKFzBWCe1mFQ006aY");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("Mjc1ucYPkDblDVkUCxVh9sidl7qzb5SQEs5J5xUMXAZtV2w0lK");
        useraccesslevel.setLevelHelp("XvhaDiGu6tLGIl2pVNak4sITI12J1OeHHDfIdUTV0DbfT3tdf8");
        useraccesslevel.setLevelName("O9yYSVF0J1yDr9sIQYnWEHuHFmd8N8aw7D3htTlWNdDauZPaU9");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("TSGyvh97IEVnjDj69WnO0v7hfdeK3XuJVaGjfd2R4pepaVzKlp");
            useraccesslevel.setUserAccessLevel(48287);
            useraccesslevel.setLevelDescription("9yVKvR4JWultkmq4VnuyRvzX64oKroo9ao83qxekPoevgHQoPW");
            useraccesslevel.setLevelHelp("ziCmRFYPtKhq87al64ODS8x32HhiWlI79HV6GjEdySFqVW9Tnt");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelName("Z8GHYxJkrD4PFKVX2xWWxDJQdGxdqHqcaNFniKB79LiZLA8Kah");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 120238));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "IufDDu2AsvQ7NFtqBN118idcYskcXNMjAPNAKKDkQMQc5AlJKBdezbByk3ABPuGMtZ8RLI5kP3mvYyLwsUTtf90RbtSaqNYtT4KsGfvvu9IhTRC72tXs92h8riLBZ6VImiYYu9X08btX7p9rs7I1mIo0FpDSs7hhHYMjEU9Azo6XnDzeZP1IR9z6FBHWSUyWMJYKVWOey6zwKS7yULKdCltwDJeUzVEju7jTcRo3kWSCfwCIpOZe4qeiYIurejrHQ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "ey0E2Xo3t9sVwpSllJ7wDdwK0Ls98nzED65iNS6GXQL1u5iVCv2ZwRFCayb9KcaOgpxS2hMJL99kICDdm9n5dj8Fp2PkWx8mAbfXDWiM7TmCJq9PrgCSWBwT6L3Tl7TaaS6r6BUXaNizJ0ANynu3Lx3JAAndtnBKkAkx2uNicNZotEZvv4Ii3fDULgUjj8jwhxtkZHdJ8tYNm6kEk3gjelqycVja372fR2kFRq92zhuYVjVvu7b6Q8mXMDqFhupmi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "rTOqIPdt5yflHZQPzt5bSi3gForWbuROPmUymNgFBwXO2imuxZpLuOeRwA9g5B1tv3yFl9Nc4D2H3WGBIW610OZzM60BNKdEhmd9sfKfjuK0JacHTBW3ELZd0zUPlORtSNOhkGLNwFK382ftujgjBDcarS2gbtoTeWUTN78kHfDuK4zp1WV5MndrJ1KW0BQPXxoLeMI9hh2mYSkODy57ZDfZiplRyz5yzKDgTftgJIOFLvYzlc9YujE5RFJ6T7hZuvoS20QFsI4Oftih3dPrGYxsplNzCnuc0iyTSxZyK3rsYsEdgYXUVsQoc8La6vZ8cRUnrNzB7HQgnBH1PUcwN3HQENF8RmQOtYdGoI8RcNAXcOTaUdbUdxehMxehfpD9OXCyqd8WALJBv7kSnvH2EIpVfUBuB30ij7BFukXHNbiJPXmvDB1biN0jontshMIvskz8FvDTzWLHT00Ndl8sTdXHHlbD4KtlBqqjmj2X1P3VFyYAXBzTVoKHoGihQ7tzPSzudIy3KZAadMHb7Df8feTfcfHDRuEKHpup8afijmZOAW7uwRfkiL2d0uSjeEWfy7ImBDRMIXFYusKQx5mMqhTYwu7lQyKqO5Q6gi2nHBVpRh5cMxDkplok5Z7blKga92pHz1avJUoJcn0Fq4yJg5NuevHrLC9eVdYrieMF53zISSsgAL4JEziBcGYN9oCZk7kyusdpNXFlxDi5elbUfwqg41WrxYzOm8oMlffZVLVwpYUlznEzZyZGhz8q1FPnEApAJjRMPHY5HzXZVDZoYeAJS1rOQNbnPYgKpIVrVbbafkcJG97NNaRkI5Ah3yxCv6GLLp1cxAJBKjNEMZyi3kp8F9F0IZJsSEaybXRnIuhg9RuUr5Kpw4iITk7GfFT1rimOJ7avHPOaDxHqZ3KR2SOZY4wllKXtN92tvpMkYZQ9RfU6jt3JMPnh3VlwQ5xjRYhUNDiYFscdAbG406oVTErhvLd3Apd1NKvdilY8cWmqf3wzA8OYvBouVUilc5mArxb2UUcs7h3mnr2bDxoS8p4DK08ufLh9Es8v1VKL8U7gLI9d7mzSqb1Z7dwBBlewHY0nZJrXuSrEb6W5uCpwECPTNNIVWT4kKdYuB4lFswiumhfoiYAlpihqJu44pmF1fsSNmmD6Y7NoY8xneqTyTDmot8xY1sUG1gBsWJfiifFQDDOu9X0tY367GNN4UtVbXBexUPxi1EN2EjgEpOqQBvoBhrPXKuDQ1oOr0V2l2O0jWMA2qph4iK5bktJHqZqIOg4FuM4KktdVpEA3tP4pxvFxPKyf0kiHLDg5TxIUHgdkri5ghrTtpQNURYGfMXxReqkeTi2kJebcjq5HtA7M74r9xmsO7h1vCaXllBRfd9293UWThUNi3odp64XaLrKor77o64dLO0gCFaki1fs97EBdsbKAWiKwFMUKKoe9IlYpWOrOqoTkAwPUDUalGsvP4kTHzMEqvhGqNtqfjdVjJetk8wqDJKVpO9ge7LtCgfJfIsLp8iN884TG2DNyZzufZyMLTxx8ggRAFSJxyFyxp95DBKsjfbJoCaFgNiK8u11ONIyRzBpObKbXVEMoRyukcMLSG6awi6gTO5YwTdto4xFD9LgVrPUjHJR7HGzFoBl2KisnkYrrmt7vma7k1G9KpR1bjKK0XhyYe0u7iuZtoPzhMuRJU7MPDIZqDwyxo9BC5htRf0gwWiE8k6xQhqRGMfKwkRv1701opLCjkLKoKMK3zWT1uFV9VDXrevcLI7x12PPGuXKte3CK1S2FqpftiR0pTW9iRksl9zlhOpAx0JCtIv8kDkjMtML9K93XIYPvM3L4qqRVod3cv9M8hA9LbgL1eOS1BAeOgKhumbSX1J96byJE30Rlnb6NJJxu5MT2bSTPkuuXzxFeyKcZSZvB7EG4v1mhZ2ZGvn7c39zuIgEMzikZ0lzTKV2ziAd6R3mZTuvbhINkpm4gVJUzhVNE4sQEXSm7dTJVqByyKOZ1mFJd0gN56WRgwBzcRv9OpkAg0MB0OBU1zoZnUby81z7pn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "Xv7K4CDjUWF3OsFwFECuhQwYdqYUykAEdvWG13XxvFLmlukqJjcbwSJd7KAur26qUz7oYUs0HW6JVQpq8CFOOdcJLPbW6eVv60b4yzMlYObZdONjQC4LLqcvgwPy8mcLEptcppPYEWE61XjOC8V6xoZ8f6O6nXG6BVLPjsOHdt8Ez51IfyuP2D40qVnAnJIL39rEl3NRrkDoH2GOC9fMl0qnwyZzduToNIDN5wl3iKwaYHhXeVmtk17IeXUaZjUMY"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
