package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {
    static EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("jpabook");
    // 매니저 팩토리 생성 비용은 크기 때문에 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 함

    public static void main(String[] args) {

        Member member = createMember("memberA", "회원1");
        member.setUsername("회원명변경");
        mergeMember(member);
    }

    static Member createMember(String id, String username) {
        // 영속성 컨텍스트1 시작
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction(); // 엔티티 매니저의 생성은 비용이 크지 않다
        tx1.begin();

        Member member = new Member(); // 엔티티 객체를 생성하고 데이터베이스에 저장하지 않았을 때를 비영속 상태라고 한다.
        member.setId(id);
        member.setUsername(username);

        em1.persist(member); // 엔티티를 영속성 컨텍스트에 저장하면 영속 상태가 된다
        tx1.commit();

        // 영속성 컨텍스트1 종료 member 엔티티는 준영속 상태가 된다
        em1.close();
        // em.detach(member); - 1차 캐시부터 쓰기 지연 SQL 저장소까지 해당 엔티티에 대한 모든 정보가 제거된다.
        // em.clear(); - 해당 영속성 컨텍스트의 모든 엔티티를 준영속 상태로 만든다.

        return member; // 준영속 상태의 엔티티 반환
    }

    static void mergeMember(Member member) {
        // 영속성 컨텍스트2 시작
        EntityManager em2= emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member); // 준영속 상태의 엔티티를 받아서 그 정보로 새로운 영속 상태의 엔티티를 반환한다.
        // merge는 비영속 엔티티도 영속 상태로 만드는 update or save 기능을 수행한다
        tx2.commit();

        System.out.println("member = " + member.getUsername()); // 준영속
        System.out.println("mergeMember = " + mergeMember.getUsername()); // 영속

        System.out.println(em2.contains(member)); // false - 영속성 컨텍스트가 파라미터로 넘어온 엔티티를 관리하는지 확인

        em2.close();
        // 영속성 컨텍스트2 종료
    }
}