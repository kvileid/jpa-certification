package no.kvileid.jpa.chapter6.persist;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import no.kvileid.jpa.PersistenceUtil;

import com.google.common.collect.Lists;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Department> departments;
    
    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class, Department.class);
        Department d1 = new Department();
        Department d2 = new Department();
        Employee e = new Employee();
        List<Department> l = Lists.newArrayList(d1, d2);
        e.departments = l;
        em.persist(e);
        PersistenceUtil.beginTransaction();
        PersistenceUtil.commitTransaction();

        PersistenceUtil.closeSession();
        EntityManager em2 = PersistenceUtil.initialize(Employee.class, Department.class);
        em2.persist(d1);
        PersistenceUtil.beginTransaction();
        PersistenceUtil.commitTransaction();
        PersistenceUtil.closeSession();
        
    }
}
