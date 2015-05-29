package no.kvileid.jpa.chapter8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;

import no.kvileid.jpa.PersistenceUtil;
import no.kvileid.jpa.chapter8.model.Address;
import no.kvileid.jpa.chapter8.model.Department;
import no.kvileid.jpa.chapter8.model.DesignProject;
import no.kvileid.jpa.chapter8.model.Employee;
import no.kvileid.jpa.chapter8.model.Phone;
import no.kvileid.jpa.chapter8.model.Project;
import no.kvileid.jpa.chapter8.model.QualityProject;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QueryTester {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception {
        EntityManager em = PersistenceUtil.initialize(Address.class, Department.class, DesignProject.class,
                Employee.class, Phone.class, Project.class, QualityProject.class);
        populate(em);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.println("JP QL> ");
            String query = reader.readLine();
            if (query.equals("quit")) {
                break;
            }
            if (query.length() == 0) {
                continue;
            }
            try {
                List result = em.createQuery(query).getResultList();
                if (result.size() > 0) {
                    int count = 0;
                    for (Object o : result) {
                        System.out.print(++count + ". ");
                        printResult(o);
                    }
                } else {
                    System.out.println("0 results returned");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PersistenceUtil.closeSession();
    }

    private static void populate(EntityManager em) {
        Address a = new Address("street");
        Department d = new Department("dep");
        Project dp = new DesignProject("dp");
        Project qp = new QualityProject("qp");
        Phone p = new Phone("12345678", "work");
        Employee e = new Employee("empName", 100L, d, dp, a);
        Employee e2 = new Employee("empName2", 1000L, d, qp, null);
        Employee e3 = new Employee("empName_", 1000L, d, dp, null);
        e.addPhone(p);
        em.getTransaction().begin();
        em.persist(e);
        em.persist(e2);
        em.persist(e3);
        em.getTransaction().commit();
    }

    private static void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.println("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.println("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }
            System.out.println("]");
        } else if (result instanceof Long || result instanceof Double || result instanceof String) {
            System.out.println(result.getClass().getName() + ": " + result);
        } else {
            System.out.println(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
    }
}
