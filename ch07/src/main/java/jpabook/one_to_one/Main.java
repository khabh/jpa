package jpabook.one_to_one;

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
        Board board = new Board();
        board.setTitle("제목");
        em.persist(board);

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setContent("내용");
        boardDetail.setBoard(board);
        em.persist(boardDetail);

        tx.commit();
        em.close();
        emf.close();
    }
}
