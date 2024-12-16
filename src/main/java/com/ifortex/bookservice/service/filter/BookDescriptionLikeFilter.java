package com.ifortex.bookservice.service.filter;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class BookDescriptionLikeFilter implements BookFilter {

    @Override
    public boolean isApplicable(SearchCriteria searchCriteria) {
        return Objects.nonNull(searchCriteria.getDescription()) && (!searchCriteria.getDescription().isEmpty() || !searchCriteria.getDescription().isBlank());
    }

    @Override
    public Stream<Book> apply(Stream<Book> books, SearchCriteria searchCriteria) {
        return books.filter(book -> book.getDescription().toLowerCase().contains(searchCriteria.getDescription().toLowerCase()));
    }
}
