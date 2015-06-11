package no.kvileid.jpa.chapter5.elementcollection;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

import com.google.common.collect.Lists;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    @Column(name = "MY_NAME")
    private List<String> names;
    @ElementCollection
    @Column(name = "EMP_ID")
    private List<Address> addresses;
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class, Address.class);
        EntityManager em = PersistenceUtil.beginTransaction();
        Address d1 = new Address();
        Address d2 = new Address();
        Employee e = new Employee();
        List<Address> l = Lists.newArrayList(d1, d2);
        e.addresses = l;
        List<String> n = Lists.newArrayList("karsten", "vileid");
        e.names = n;
        em.persist(e);
        PersistenceUtil.commitTransaction();
        PersistenceUtil.closeSession();
    }
}
