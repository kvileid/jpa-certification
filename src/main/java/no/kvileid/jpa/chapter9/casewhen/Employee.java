package no.kvileid.jpa.chapter9.casewhen;

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
    private int salary;
    private String city;

    protected Employee() {
    }

    public Long getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public String getCity() {
        return city;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary;
        this.city = city;
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class, TromsøProject.class, OsloProject.class, Project.class);
        PersistenceUtil.beginTransaction();
        Employee e1 = new Employee(100, "Oslo");
        Employee e2 = new Employee(200, "Tromsø");

        em.persist(e1);
        em.persist(e2);

        PersistenceUtil.commitTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Employee> emp = query.from(Employee.class);
        query.multiselect(emp.get("salary") 
                , cb.selectCase()
                    .when(cb.equal(emp.get("salary"), 200) ,"Woow")
                    .when(cb.equal(emp.get("salary"), 100), "Ask for more")
                    .otherwise("What2")
                    );
        TypedQuery<Object[]> q = em.createQuery(query);
        for (Object o : q.getResultList()) {
            System.out.println(o);
        }
        PersistenceUtil.closeSession();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
