package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class BookGenreFilter implements BookFilter {

    @Override
    public boolean isApplicable(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getGenre()) && (!searchCriteria.getGenre().isEmpty() || !searchCriteria.getGenre().isBlank());
    }

    @Override
    public Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria) {
        return books.filter(book -> book.getGenres().contains(searchCriteria.getGenre()));
    }
}
