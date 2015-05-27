package no.kvileid.jpa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.AvailableSettings;
//import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


@SuppressWarnings("deprecation")
public class PersistenceUtil {
    private static EntityManager entityManager;

    public static void initialize(Class<?>... entityClasses) {
        recreateDatabase(entityClasses);
        entityManager = createFactory(entityClasses).createEntityManager();
    }

    public static EntityManager beginTransaction() {
        entityManager.getTransaction().begin();

        return entityManager;
    }

    public static void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    public static void closeSession() {
        // Closing entityManagerFactory required due to bug in Hibernate, see http://stackoverflow.com/questions/21645516/program-using-hibernate-does-not-terminate
        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }

//    private static void recreateDatabase(Class<?>... entityClasses) {
//        Ejb3Configuration config = new Ejb3Configuration().configure("JpaCert", null);
//        for (Class<?> clazz : entityClasses) {
//            config.addAnnotatedClass(clazz);
//        }
//        new SchemaExport(config.getHibernateConfiguration()).create(true, true);
//    }

    private static EntityManagerFactory createFactory(Class<?>... entityClasses) {
        Map<String, Object> props = new HashMap<>();
        props.put(org.hibernate.jpa.AvailableSettings.LOADED_CLASSES, Arrays.asList(entityClasses));
        return Persistence.createEntityManagerFactory("JpaCert", props);
    }

    private static void recreateDatabase(Class<?>... entityClasses) {
//    PersistenceXmlParser parser = new PersistenceXmlParser(new ClassLoaderServiceImpl(), PersistenceUnitTransactionType.RESOURCE_LOCAL);
//    ParsedPersistenceXmlDescriptor descriptors = parser.doResolve(new HashMap<>()).get(0);

        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:h2:/var/tmp/h2/jpacert;AUTO_SERVER=TRUE");
        cfg.setProperty("hibernate.connection.username", "sa");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.show-sql", "true");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        for (Class<?> clazz : entityClasses) {
            cfg.addAnnotatedClass(clazz);
        }
        new SchemaExport(cfg).create(true, true);
    }
}
