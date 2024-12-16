package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.repository.BookRepository;
import com.ifortex.bookservice.service.BookService;
import com.ifortex.bookservice.service.filter.BookFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomESBookServiceImpl implements BookService {

    private final BookRepository repository;
    private final List<BookFilter> bookFilters;

    @Override
    @Transactional
    public Map<String, Long> getBooks() {
        return repository.findAll().stream()
                .flatMap(book -> book.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    @Transactional
    public List<Book> getAllByCriteria(SearchCriteria searchCriteria) {
        List<Book> allBooks = repository.findAll();

        if (searchCriteria == null) {
            return allBooks;
        }

        List<Book> filteredBooks = bookFilters.stream()
                .filter(filter -> filter.isApplicable(searchCriteria))
                .flatMap(filter -> filter.apply(allBooks.stream(), searchCriteria))
                .toList();

        List<Book> booksToReturn = filteredBooks.isEmpty() ? allBooks : filteredBooks;

        return booksToReturn.stream()
                .sorted(Comparator.comparing(Book::getPublicationDate).reversed())
                .toList();
    }

}
