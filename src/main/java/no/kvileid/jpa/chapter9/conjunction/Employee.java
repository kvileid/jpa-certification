package no.kvileid.jpa.chapter9.conjunction;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private final String name;
    private final Integer salary;

    public Employee(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class);
        PersistenceUtil.beginTransaction();
        Employee e1 = new Employee("Karsten", 100);
        Employee e2 = new Employee("Test", 200);

        em.persist(e1);
        em.persist(e2);

        PersistenceUtil.commitTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);
        
        Predicate criteria = cb.disjunction();
        criteria = cb.or(criteria, cb.equal(emp.get("name"), "Karsten"));
        c.where(criteria);
        TypedQuery<Employee> q = em.createQuery(c);

        List<Employee> es = q.getResultList();
        System.out.println(es.size());

        PersistenceUtil.closeSession();
    }
}
