package kr.co.sample.jdbctest.dao;

import kr.co.entity.Member;
import kr.co.entity.MemberType;
import kr.co.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class InsertTest {
    /**
     * EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
     * EntityManager는 쓰레드간에 공유하며 안된다. (사용하고 버려야한다.)
     * JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
     */

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-db");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA1");

            em.persist(team);

            // 등록
            Member member = new Member();
            member.setName("hello");
            member.setMemberType(MemberType.USER);

            // 주인이 아님으로 MEMBER_ID 값 변경 안됨 (하지만 양쪽다 넣는걸로 추천)
//            team.getMembers().add(member);


            // 참조 대신에 외래 키를 그대로 사용
//            member.setTeamId(team.getId());
            // 팀객체를 넣어준다.
            member.setTeam(team);

            em.persist(member);


            // 임시
            em.flush(); // DB에 쿼리를 다 반영한다.
            em.clear(); // 영속성 컨텍스트에 있는 캐시 모두 날려버린다.


            // 조회
            Member findMember = em.find(Member.class, member.getId());
            /*
            Long teamdId = findMember.getTeamId();

            // 연관관계가 없음
            Team findTeam = em.find(Team.class, teamdId);
            */
            // 참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();

            List<Member> members = findTeam.getMembers();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
