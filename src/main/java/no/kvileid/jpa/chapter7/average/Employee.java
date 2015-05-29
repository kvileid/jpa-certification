package no.kvileid.jpa.chapter7.average;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import no.kvileid.jpa.PersistenceUtil;

@Entity
@NamedQueries ({
        @NamedQuery(name = "findAllE", query = "select e from Employee e"),
        @NamedQuery(name = "findAllD", query = "select d from Department d")
})
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private final Integer salary;
    @ManyToOne(cascade = CascadeType.ALL)
    private final Department department;
    
    protected Employee() {
        this.salary = null;
        this.department = null;
    }
    
    public Employee(Integer salary, Department department) {
        this.salary = salary;
        this.department = department;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class, Department.class);
        PersistenceUtil.beginTransaction();
        Department d1 = new Department("Dev1");
        Department d2 = new Department("Dev2");
        Employee e1 = new Employee(100, d1);
        Employee e2 = new Employee(200, d1);
        Employee e3 = new Employee(300, d2);
        Employee e4 = new Employee(400, d2);

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);
        PersistenceUtil.commitTransaction();
        
        TypedQuery<Double> q = em.createQuery("select avg(e.salary) from Employee e", Double.class);
        System.out.println(q.getSingleResult());
        
        for (Employee e : em.createNamedQuery("findAllE", Employee.class).getResultList()) {
            System.out.println(e.salary);
        }
        
        for (Department d : em.createNamedQuery("findAllD", Department.class).getResultList()) {
            System.out.println(d);
        }

        TypedQuery<Object[]> pQ = em.createQuery("select e.department.depName, avg(e.salary) from Employee e group by e.department.depName", Object[].class);
        for (Object[] arr : pQ.getResultList()) {
            System.out.println(arr[0] + " : " + arr[1]);
        }
        PersistenceUtil.closeSession();
    }
}
