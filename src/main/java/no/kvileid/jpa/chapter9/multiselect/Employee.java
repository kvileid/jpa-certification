package no.kvileid.jpa.chapter9.multiselect;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Tuple;
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
    private String name;
    private Integer salary;

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
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("name"), emp.get("salary"));
        TypedQuery<Object[]> q = em.createQuery(c);
        for (Object[] objs : q.getResultList()) {
            for (Object o : objs) {
                System.out.println(o);
            }
        }

        CriteriaQuery<Tuple> c2 = cb.createTupleQuery();
        Root<Employee> emp2 = c2.from(Employee.class);
        c2.multiselect(emp2.get("name"), emp2.get("salary"), emp2.get("id"));
        TypedQuery<Tuple> q2 = em.createQuery(c2);
        for (Tuple t : q2.getResultList()) {
            System.out.println(t.get(0));
            System.out.println(t.get(1));
            System.out.println(t.get(2));
        }

        CriteriaQuery<EmployeeInfo> c3 = cb.createQuery(EmployeeInfo.class);
        Root<Employee> emp3 = c3.from(Employee.class);
        c3.multiselect(emp3.get("name"), emp3.get("salary"));
        TypedQuery<EmployeeInfo> eis = em.createQuery(c3);
        for (EmployeeInfo ei : eis.getResultList()) {
            System.out.println(ei);
        }
        
        cb.disjunction();
        PersistenceUtil.closeSession();
    }
}
