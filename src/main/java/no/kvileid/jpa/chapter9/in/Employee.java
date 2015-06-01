package no.kvileid.jpa.chapter9.in;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private String city;
    
    protected Employee() {
        this.salary = null;
    }
    
    public Employee(Integer salary, String city) {
        this.salary = salary;
        this.city = city;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class);
        PersistenceUtil.beginTransaction();
        Employee e1 = new Employee(100, "Oslo");
        Employee e2 = new Employee(200, "Tromsø");

        em.persist(e1);
        em.persist(e2);

        PersistenceUtil.commitTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.in(emp.get("city")).value("Oslo").value("Tromsø"));
        TypedQuery<Employee> q = em.createQuery(c);
        System.out.println(q.getResultList().size());

        CriteriaQuery<Employee> c2 = cb.createQuery(Employee.class);
        Root<Employee> emp2 = c2.from(Employee.class);
        c2.select(emp).where(emp2.get("city").in("Oslo", "Tromsø"));
        TypedQuery<Employee> q2 = em.createQuery(c2);
        System.out.println(q2.getResultList().size());

        PersistenceUtil.closeSession();
    }
}
