package no.kvileid.jpa.chapter12.validation;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import no.kvileid.jpa.PersistenceUtil;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String salary;
    @NotNull
    private String city;

    protected Employee() {
        // this.salary = null;
    }

    public Employee(Integer salary, String city) {
        this.salary = salary.toString();
        this.city = city;
    }

    @PrePersist
    public void hello() {
        System.out.println("Anytime: " + city);
    }
    

    
    public static void main(String[] args) {
        try {
            EntityManager em = PersistenceUtil.initialize(Employee.class);
            PersistenceUtil.beginTransaction();
            Employee e1 = new Employee(100, null);
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
