package no.kvileid.jpa.chapter7.flushmode;

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
    private Integer salary;
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
        Employee e1 = new Employee(100, d1);
        Employee e2 = new Employee(200, d1);

        em.persist(e1);
        em.persist(e2);
        List<Employee> emps = em.createQuery("select e from Employee e", Employee.class).getResultList();

        PersistenceUtil.commitTransaction();
        
        PersistenceUtil.beginTransaction();
        emps.get(0).salary = 1000;
        TypedQuery<Employee> q = em.createQuery("select e from Employee e", Employee.class);
        q.setFlushMode(FlushModeType.COMMIT);
        q.getResultList();
        PersistenceUtil.commitTransaction();

        PersistenceUtil.closeSession();
    }
}
