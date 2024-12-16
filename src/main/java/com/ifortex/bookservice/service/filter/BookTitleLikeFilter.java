package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class BookTitleLikeFilter implements BookFilter {

    @Override
    public boolean isApplicable(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getTitle()) && (!searchCriteria.getTitle().isEmpty() || !searchCriteria.getTitle().isBlank());
    }

    @Override
    public Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria) {
        return books.filter(book -> book.getTitle().toLowerCase().contains(searchCriteria.getTitle().toLowerCase()));
    }
}
