package no.kvileid.jpa.havard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private final String name;
    private final Integer salary;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn
    private List<Project> projects;
    
    public Employee() {
        this.name = null;
        this.salary = null;
    }
    
    public Employee(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
        this.projects = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class, Project.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new Employee("Karsten", 100);
            Project p = new Project("importantProj");
            Project p2 = new Project("notimportantProj");
            e1.projects.add(p);
            e1.projects.add(p2);
            
            em.persist(e1);
            
            PersistenceUtil.commitTransaction();
//            PersistenceUtil.clear();
            PersistenceUtil.beginTransaction();
            String hql = "select e from Employee e where id = 1";
            TypedQuery<Employee> q = em.createQuery(hql, Employee.class);
            System.out.println("HEI");
            Employee emp = q.getSingleResult();
            emp.projects.remove(0);
            PersistenceUtil.commitTransaction();
        } finally {
            PersistenceUtil.closeSession();
        }
    }
}
