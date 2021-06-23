package kr.co.sample.jdbctest.dao;

import kr.co.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            Member member = new Member();
            member.setId(2L);
            member.setName("hello");

            em.persist(member);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
