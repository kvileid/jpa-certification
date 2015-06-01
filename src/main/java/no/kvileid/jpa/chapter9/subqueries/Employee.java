package no.kvileid.jpa.chapter9.subqueries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;
    
    protected Employee() {
        this.salary = null;
    }
    
    public Employee(Integer salary, Project p) {
        this.salary = salary;
        this.projects = new ArrayList<Project>();
        this.projects.add(p);
    }

    public static void main(String[] args) {
        EntityManager em = PersistenceUtil.initialize(Employee.class, Project.class);
        PersistenceUtil.beginTransaction();
        Employee e1 = new Employee(100, new Project("p1"));
        Employee e2 = new Employee(200, new Project("p2"));

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
