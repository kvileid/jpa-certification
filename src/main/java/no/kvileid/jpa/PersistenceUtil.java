package no.kvileid.jpa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;

import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.AvailableSettings;
import org.hibernate.jpa.boot.internal.ParsedPersistenceXmlDescriptor;
import org.hibernate.jpa.boot.internal.PersistenceXmlParser;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class PersistenceUtil {
    private static EntityManager entityManager;

    public static EntityManager initialize(Class<?>... entityClasses) {
        recreateDatabase(entityClasses);
        entityManager = createFactory(entityClasses).createEntityManager();
        return entityManager;
    }

    public static EntityManager beginTransaction() {
        entityManager.getTransaction().begin();
        return entityManager;
    }

    public static void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    public static void save(Object o) {
        beginTransaction();
        entityManager.persist(o);
        commitTransaction();
    }
    
    public static void closeSession() {
        // Closing entityManagerFactory required due to bug in Hibernate, see
        // http://stackoverflow.com/questions/21645516/program-using-hibernate-does-not-terminate
        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }

    public static EntityManager reinitialise(Class<?>... entityClasses) {
        entityManager = createFactory(entityClasses).createEntityManager();
        return entityManager;
    }
    
    private static EntityManagerFactory createFactory(Class<?>... entityClasses) {
        Map<String, Object> props = new HashMap<>();
        props.put(AvailableSettings.LOADED_CLASSES, Arrays.asList(entityClasses));
        props.put(AvailableSettings.INTERCEPTOR, "no.kvileid.jpa.MyInterceptor");
        return Persistence.createEntityManagerFactory("JpaCert", props);
    }

    private static void recreateDatabase(Class<?>... entityClasses) {
        PersistenceXmlParser parser = new PersistenceXmlParser(new ClassLoaderServiceImpl(),
                PersistenceUnitTransactionType.RESOURCE_LOCAL);
        ParsedPersistenceXmlDescriptor descriptor = parser.doResolve(new HashMap<>()).get(0);

        
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", getProperty(descriptor, AvailableSettings.JDBC_DRIVER));
        cfg.setProperty("hibernate.connection.url", getProperty(descriptor, AvailableSettings.JDBC_URL));
        cfg.setProperty("hibernate.connection.username", getProperty(descriptor, AvailableSettings.JDBC_USER));
        cfg.setProperty("hibernate.connection.password", getProperty(descriptor, AvailableSettings.JDBC_PASSWORD));
        cfg.setProperty("hibernate.show-sql", "true");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        for (Class<?> clazz : entityClasses) {
            cfg.addAnnotatedClass(clazz);
        }
        new SchemaExport(cfg).create(false, true);
    }

    private static String getProperty(ParsedPersistenceXmlDescriptor descriptor, String propertyName) {
        return descriptor.getProperties().getProperty(propertyName);
    }

    public static void clear() {
        entityManager.clear();
    }
    
}
