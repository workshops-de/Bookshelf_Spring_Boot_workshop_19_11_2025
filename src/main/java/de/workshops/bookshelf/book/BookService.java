package de.workshops.bookshelf.book;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class BookService {

  private final BookRepository bookRepository;

  List<Book> getAllBooks() throws BookNotFoundException {
    return bookRepository
        .findAll()
        .stream()
        .collect(
            Collectors.collectingAndThen(
                Collectors.toList(), books -> {
                  if (books.isEmpty()) {
                    throw new BookNotFoundException();
                  }

                  return books;
                }
            )
        );
  }

  Book searchBookByIsbn(String isbn) throws BookNotFoundException {
    return bookRepository
        .findAll()
        .stream()
        .filter(book -> hasIsbn(book, isbn))
        .findFirst()
        .orElseThrow(BookNotFoundException::new);
  }

  List<Book> searchBookByAuthor(String author) throws BookNotFoundException {
    return bookRepository
        .findAll()
        .stream()
        .filter(book -> hasAuthor(book, author))
        .collect(
            Collectors.collectingAndThen(
                Collectors.toList(), books -> {
                  if (books.isEmpty()) {
                    throw new BookNotFoundException();
                  }

                  return books;
                }
            )
        );
  }

  List<Book> searchBooks(BookSearchRequest request) throws BookNotFoundException {
    return bookRepository
        .findAll()
        .stream()
        .filter(book -> hasAuthor(book, request.author()))
        .filter(book -> hasIsbn(book, request.isbn()))
        .collect(
            Collectors.collectingAndThen(
                Collectors.toList(), books -> {
                  if (books.isEmpty()) {
                    throw new BookNotFoundException();
                  }

                  return books;
                }
            )
        );
  }

  Book createBook(Book book) {
    return book;
  }

  private boolean hasIsbn(Book book, String isbn) {
    return book.getIsbn().equals(isbn);
  }

  private boolean hasAuthor(Book book, String author) {
    return book.getAuthor().contains(author);
  }
}
