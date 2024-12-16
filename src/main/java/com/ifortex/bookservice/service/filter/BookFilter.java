package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;

import java.util.stream.Stream;

public interface BookFilter {

    boolean isApplicable(SearchCriteria searchCriteria);

    Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria);
}
