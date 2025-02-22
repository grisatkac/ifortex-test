package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Member;

import java.util.List;

public interface ICustomMemberRepository {

    List<Member> findMembersByYearWithoutBooks(int year);

    List<Member> findAll();
}
