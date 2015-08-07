package no.kvileid.jpa.demo;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee extends BaseEntity {
    private final String name;
    
    public Employee(String name) {
        this.name = checkNotNull(name);
    }

    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class);
        PersistenceUtil.save(new Employee("Karsten"));
        PersistenceUtil.closeSession();

        EntityManager em = PersistenceUtil.reinitialise(Employee.class);
        Employee ee = em.find(Employee.class, 1L);
        System.out.println(ee.name);
        
        PersistenceUtil.closeSession();
    }
}
