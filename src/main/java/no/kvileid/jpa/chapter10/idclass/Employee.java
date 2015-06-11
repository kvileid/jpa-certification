package no.kvileid.jpa.chapter10.idclass;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import no.kvileid.jpa.PersistenceUtil;

@Entity
@IdClass(EmployeeId.class)
public class Employee {
    @Id
    private Long id;
    @Id
    private String country;
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
            e1.id = 10L;
            e1.country = "Norway";
            
            Employee e2 = new Employee(200, "Tromsø");
            e2.id = 10L;
            e2.country = "Sweden";
            
            em.persist(e1);
            em.persist(e2);

            PersistenceUtil.commitTransaction();
            PersistenceUtil.clear();
            
            EmployeeId id = new EmployeeId("Sweden", 10L);
            Employee e = em.find(Employee.class, id);
            System.out.println(e);
            
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
