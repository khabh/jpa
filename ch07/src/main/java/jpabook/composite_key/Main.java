package jpabook.composite_key;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    private final static EntityManager em = emf.createEntityManager();
    private final static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
            tx.begin();
            Parent parent = new Parent();
//            parent.setId1("myId1");
//            parent.setId2("myId2");
//            parent.setId(new ParentId("myId1", "myId2"));
            parent.setName("parentName");
            em.persist(parent);

//            ParentId parentId = new ParentId("myId1", "myId2");
//            Parent parent1 = em.find(Parent.class, parentId);
//            System.out.println(parent1.getName());
            tx.commit();
            em.close();
            emf.close();
    }
}
