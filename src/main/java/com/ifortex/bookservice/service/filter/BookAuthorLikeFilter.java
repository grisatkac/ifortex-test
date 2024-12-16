package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class BookAuthorLikeFilter implements BookFilter{

    @Override
    public boolean isApplicable(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getAuthor()) && (!searchCriteria.getAuthor().isEmpty() || !searchCriteria.getAuthor().isBlank());
    }

    @Override
    public Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria) {
        return books.filter(book -> book.getAuthor().toLowerCase().contains(searchCriteria.getAuthor().toLowerCase()));
    }
}
