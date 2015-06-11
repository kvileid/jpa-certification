package no.kvileid.jpa.chapter11.resultsetmapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import no.kvileid.jpa.PersistenceUtil;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SqlResultSetMapping(name = "EmployeeMapping", entities = @EntityResult(entityClass = Employee.class
    , fields = @FieldResult(name = "city", column = "city")
) )
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String salary;
    private String city;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Evaluation eval;

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
//            e1.eval = new Evaluation();
            em.persist(e1);
            PersistenceUtil.commitTransaction();
            PersistenceUtil.clear();

            String sql = "select id, salary, city from Employee";
            Query q = em.createNativeQuery(sql, "EmployeeMapping");
            q.getResultList();

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
