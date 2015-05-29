package no.kvileid.jpa.chapter5.orderedlist;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

import com.google.common.collect.Lists;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    @OrderColumn
    @OrderBy("id")
    private List<Department> department;
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class, Department.class);
        EntityManager em = PersistenceUtil.beginTransaction();
        Department d1 = new Department();
        Department d2 = new Department();
        Employee e = new Employee();
        List<Department> l = Lists.newArrayList(d1, d2);
        e.department = l;
        em.persist(e);
        PersistenceUtil.commitTransaction();
        PersistenceUtil.closeSession();
        
    }
}
