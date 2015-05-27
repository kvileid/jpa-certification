package no.kvileid.jpa.chapter4.access.field;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

// Note name is final and no no-arg constructor
// JPA says needs protected or public no-arg constructor. However Hibernate does not need it.
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private final String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(User.class);
        
        EntityManager em = PersistenceUtil.beginTransaction();
        User u = new User("myname");
        em.persist(u);
        PersistenceUtil.commitTransaction();
        
        User uu = em.find(User.class, 1L);
        System.out.println(uu.name);
        PersistenceUtil.closeSession();
    }
}
