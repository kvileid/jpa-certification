package no.kvileid.jpa.chapter10.orphanremoval;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<Evaluation> evals = new ArrayList<>();

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary.toString();
        this.city = city;
    }

    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class, Evaluation.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new Employee(100, "Oslo");
            List<Evaluation> evs  = new ArrayList<>();
            evs.add(new Evaluation());
            e1.evals = evs;
            em.persist(e1);
            PersistenceUtil.commitTransaction();

            e1 = em.find(Employee.class, 1L);
            e1.evals.remove(0);
            
            PersistenceUtil.beginTransaction();
            PersistenceUtil.commitTransaction();
            
            PersistenceUtil.clear();
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
