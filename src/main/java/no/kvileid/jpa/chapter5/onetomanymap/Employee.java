package no.kvileid.jpa.chapter5.onetomanymap;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import no.kvileid.jpa.PersistenceUtil;

@Entity
@Transactional
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "emp")
    @MapKeyColumn(name="addr_type", nullable=true)
    private Map<String, Address> addresses;
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class, Address.class);
        EntityManager em = PersistenceUtil.beginTransaction();
        Address d1 = new Address();
        Address d2 = new Address();
        Employee e = new Employee();
        em.persist(e);
        em.flush();
        d1.emp = e;
        d2.emp = e;
        em.persist(d1);
        em.persist(d2);
        Map<String, Address> a = new HashMap<>();
        a.put("home", d1);
        a.put("work", d2);
        e.addresses = a;
//        em.persist(e);
        PersistenceUtil.commitTransaction();
        PersistenceUtil.closeSession();
    }
}
