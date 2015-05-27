package no.kvileid.jpa.chapter4.access.mixed;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

@Entity
@Access(AccessType.FIELD)
public class UserBase {
    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private final String name;
    
    public UserBase(String name) {
        this.name = name;
    }

    protected UserBase() {
        this.name = null;
    }
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(UserBase.class);
        
        EntityManager em = PersistenceUtil.beginTransaction();
        UserBase u = new UserBase("myname");
        em.persist(u);
        PersistenceUtil.commitTransaction();
        PersistenceUtil.clear();
        
        EntityManager em2 = PersistenceUtil.beginTransaction();
        UserBase uu = em2.find(UserBase.class, 1L);
        System.out.println(uu.name);
        PersistenceUtil.commitTransaction();;
        PersistenceUtil.closeSession();
    }
}
