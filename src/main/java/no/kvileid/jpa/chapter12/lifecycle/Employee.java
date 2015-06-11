package no.kvileid.jpa.chapter12.lifecycle;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.transaction.UserTransaction;

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

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        EntityTransaction et;
        UserTransaction ut;
        this.salary = salary.toString();
        this.city = city;
    }

    @PrePersist
    public void hello() {
        System.out.println("Anytime");
    }
    
    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class, FulltimeEmployee.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new FulltimeEmployee(100, "Oslo");
            em.persist(e1);
            PersistenceUtil.commitTransaction();
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
