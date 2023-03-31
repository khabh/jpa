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
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성 (역할 - 수정/등록/삭제/조회) 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용
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
        // 식별자를 기준으로 조회하는 find() 메소드를 호출할 때는 플러시가 실행되지 않는다.
        List<Member> members = query.getResultList();
        System.out.println("members.size=" + members.size());

        em.remove(member); // 엔티티 객체에서 삭제된 상태
    }
}
