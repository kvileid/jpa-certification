package no.kvileid.jpa.chapter9.coalesce;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
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
    private String salary;
    private String city;

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary.toString();
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

            String hql = "select coalesce(e.city, e.salary) from Employee e";
            Query q = em.createQuery(hql);

            //            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Object> c = cb.createQuery();
//            Root<Employee> emp = c.from(Employee.class);
//            c.multiselect(cb.coalesce(emp.get("salary"), emp.get("city")));
//            TypedQuery<Object> q = em.createQuery(c);
            
            for (Object o : q.getResultList()) {
                System.out.println(o);
            }
        } finally {
            PersistenceUtil.closeSession();
        }
    }
}
