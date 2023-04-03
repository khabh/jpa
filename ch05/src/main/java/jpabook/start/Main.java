package jpabook.start;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    private static EntityManager em = emf.createEntityManager();
    private static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        tx.begin();
        testSave();
        queryLogicJoin();
//        updateRelation();
//        deleteRelation();
        biDirection();
        tx.commit();
        em.close();
        emf.close();
    }

    private static void testSave() {
        Team team = new Team("team1", "팀1");
        em.persist(team);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team);
        em.persist(member2);
    }

    private static void queryLogicJoin() {
        String jpql = "select m from Member m join m.team t WHERE t.name = :teamName";

        List<Member> members = em.createQuery(jpql, Member.class)
            .setParameter("teamName", "팀1")
            .getResultList();

        for (Member member : members) {
            System.out.println("member.username = " + member.getUsername());
        }
    }

    private static void updateRelation() {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void deleteRelation() {
        Team team1 = em.find(Team.class, "team1");
        Member member2 = em.find(Member.class, "member2");
        member2.setTeam(null); // 연관 관계를 끊지 않으면 데이터베이스 오류가 발생한다.
//        ERROR: Referential integrity constraint violation: "FKL7WSNY760HJY6X19KQNDUASBM:
//        PUBLIC.MEMBER FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(TEAM_ID) ('team1')";
        em.remove(team1);
    }


    private static void biDirection() {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for (Member member : members) {
            System.out.println("member.username = " + member.getUsername());
        }
    }

    private static void testSaveNonOwner() {

        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team = new Team("team1", "팀1");
        team.getMembers().add(member1);
        team.getMembers().add(member2);

        em.persist(team); // 연관관계 주인에 값을 저장하지 않았기 때문에 member 테이블에 team 정보가 저장되지 않음
    }
}

