package no.kvileid.jpa.chapter9.select;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private Integer salary;
    
    protected Employee() {
        this.salary = null;
    }
    
    public Employee(Integer salary) {
        this.salary = salary;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class);
        PersistenceUtil.beginTransaction();
        Employee e1 = new Employee(100);
        Employee e2 = new Employee(200);

        em.persist(e1);
        em.persist(e2);

        PersistenceUtil.commitTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);
        TypedQuery<Employee> q = em.createQuery(c);
        System.out.println(q.getResultList().size());

        PersistenceUtil.closeSession();
    }
}
