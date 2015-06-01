package no.kvileid.jpa.chapter9.metamodel;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private int salary;
    private String city;

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary;
        this.city = city;
    }

    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new Employee(100, null);
            Employee e2 = new Employee(200, "Tromsø");

            em.persist(e1);
            em.persist(e2);

            PersistenceUtil.commitTransaction();
            PersistenceUtil.clear();

            String hql = "select e.salary, e.city from Employee e order by city desc nulls first";
            TypedQuery<Object[]> q = em.createQuery(hql, Object[].class);
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
//            Root<Employee> emp = c.from(Employee.class);
//            c.multiselect(emp.get("salary"), emp.get("city"));
//            c.orderBy(cb.asc(emp.get("salary")));
//            TypedQuery<Object[]> q = em.createQuery(c);
            
            for (Object[] o : q.getResultList()) {
                System.out.println(o[0] + " : " + o[1]);
            }
        } finally {
            PersistenceUtil.closeSession();
        }
    }
}
