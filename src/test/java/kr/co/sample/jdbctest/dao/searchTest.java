package kr.co.sample.jdbctest.dao;

import kr.co.entity.Member;
import kr.co.entity.MemberType;
import kr.co.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class searchTest {
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

            // 참조 대신에 외래 키를 그대로 사용
//            member.setTeamId(team.getId());
            // 팀객체를 넣어준다.
            member.setTeam(team);

            em.persist(member);


            // 임시
            em.flush();
            em.clear();


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

            String jpql = "SELECT m FROM Member m WHERE m.name like '%hello%'";
            List<Member> result = em.createQuery(jpql, Member.class).getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
