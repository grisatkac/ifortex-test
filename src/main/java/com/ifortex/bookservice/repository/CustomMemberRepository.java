package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomMemberRepository implements ICustomMemberRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Member> findMembersByYearWithoutBooks(int year) {
        String query = """
            SELECT * FROM members m
            WHERE YEAR(m.membership_date) = :year
            AND m.id NOT IN (
                SELECT member_id FROM member_books
            )
        """;

        return entityManager.createNativeQuery(query, Member.class)
                .setParameter("year", year)
                .getResultList();
    }

    @Override
    public List<Member> findAll() {
        String query = """
        SELECT m 
        FROM Member m
    """;

        return entityManager.createQuery(query, Member.class)
                .getResultList();
    }
}
