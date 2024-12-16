package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class BookYearFilter implements BookFilter {

    @Override
    public boolean isApplicable(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getYear()) && searchCriteria.getYear() != 0;
    }

    @Override
    public Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria) {
        return books.filter(book -> Objects.equals(book.getPublicationDate().getYear(), searchCriteria.getYear()));
    }
}
