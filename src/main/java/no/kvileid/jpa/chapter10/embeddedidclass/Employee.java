package no.kvileid.jpa.chapter10.embeddedidclass;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import no.kvileid.jpa.PersistenceUtil;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Employee {
    @EmbeddedId
    private EmployeeId id;
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
            EmployeeId id1 = new EmployeeId("Norway", 10L);
            e1.id = id1;
            
            Employee e2 = new Employee(200, "Tromsø");
            EmployeeId id2 = new EmployeeId("Sweden", 10L);
            e2.id = id2;
            
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
