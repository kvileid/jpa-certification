package no.kvileid.jpa.chapter6.reference;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class);
        PersistenceUtil.beginTransaction();
        Employee e = new Employee();
        em.persist(e);
        PersistenceUtil.commitTransaction();

        PersistenceUtil.closeSession();

        EntityManager em2 = PersistenceUtil.reinitialise(Employee.class);
//        PersistenceUtil.beginTransaction();
        Employee ee = em2.getReference(Employee.class, 1L);
        System.out.println(e.getId());
        System.out.println(ee.getId());
//        PersistenceUtil.commitTransaction();
        PersistenceUtil.closeSession();
        
    }
}
