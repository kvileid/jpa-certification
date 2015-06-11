package no.kvileid.jpa.chapter12.version;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import no.kvileid.jpa.PersistenceUtil;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String salary;
    private String city;
    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Evaluation> evals;
    @Version
    private int version;

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary.toString();
        this.city = city;
        this.evals = new ArrayList<Evaluation>();
    }

    @PreUpdate
    @PrePersist
    public void hello() {
        System.out.println("Anytime: " + city);
    }
    
    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class, Evaluation.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new Employee(100, "Oslo");
            e1.evals.add(new Evaluation());
            em.persist(e1);
            PersistenceUtil.commitTransaction();
            PersistenceUtil.clear();
            e1 = em.find(Employee.class, 1L);
            PersistenceUtil.beginTransaction();
            e1.evals.add(new Evaluation());
            System.out.println(e1.evals.size());
            System.out.println("before commit");
            PersistenceUtil.commitTransaction();
            System.out.println(e1.evals.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PersistenceUtil.closeSession();
        }
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
