package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.CustomMemberRepository;
import com.ifortex.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class CustomESMemberServiceImpl implements MemberService {

    private final CustomMemberRepository repository;

    @Override
    public Member findMember() {
        List<Member> members = repository.findAll();
        String genre = "Romance";

        Optional<Member> optional = members.stream()
                .filter(member -> member.getBorrowedBooks().stream()
                        .anyMatch(book -> book.getGenres().contains(genre)))
                .min(Comparator.comparing((Member m) -> m.getBorrowedBooks().stream()
                                .filter(book -> book.getGenres().contains(genre))
                                .map(Book::getPublicationDate)
                                .min(Comparator.naturalOrder())
                                .orElse(LocalDateTime.MAX))
                .thenComparing(Member::getMembershipDate));

        return optional.orElse(null);
    }

    @Override
    @Transactional
    public List<Member> findMembers() {
        return repository.findMembersByYearWithoutBooks(2023);
    }
}
