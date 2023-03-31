package jpabook.start;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook"); // 유닛 이름이 jpabook인 설정 정보 조회해서 매니저 팩토리 생성
        // 매니저 팩토리 생성 비용은 크기 때문에 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 함
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성 (역할 - 수정/등록/삭제/조회) 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용 X
        EntityTransaction tx = em.getTransaction();

        try {  // 트랜잭션 관리
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 사용이 끝난 엔티티 매니저 및 엔티티 매니저 팩토리 종료 필수
        }
        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        em.persist(member);
        member.setAge(20); // jpa는 어떤 엔티티가 변경되었는지 추적하는 기능이 있기 때문에 엔티티 값만 변경하면 데이터베이스 값도 함께 변경된다

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class); // JPQL은 엔티티 객체를 대상으로 쿼리한다. Member는 테이블이 아닌 회원 엔티티 객체를 의미한다.
        List<Member> members = query.getResultList();
        System.out.println("members.size=" + members.size());

        em.remove(member);
    }
}
