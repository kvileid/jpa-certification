package no.kvileid.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersistenceUtil.initialize(User.class);
		EntityManager entityManager = PersistenceUtil.beginTransaction();
		User u = new User();
		u.setName("hei");
		entityManager.persist(u);
		PersistenceUtil.commitTransaction();
		User uu = entityManager.find(User.class, 1);
		System.out.println(uu.getName());
		PersistenceUtil.closeSession();
	}
}
