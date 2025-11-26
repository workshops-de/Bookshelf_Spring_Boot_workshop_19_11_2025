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
    final var books = bookRepository.findAll();

    return getBookList(books);
  }

  Book searchBookByIsbn(String isbn) throws BookNotFoundException {
    final var book = bookRepository.findByIsbn(isbn);
    if (book == null) {
      throw new BookNotFoundException();
    }

    return book;
  }

  List<Book> searchBookByAuthor(String author) throws BookNotFoundException {
    return getBookList(bookRepository.findByAuthorContaining(author));
  }

  List<Book> searchBooks(BookSearchRequest request) throws BookNotFoundException {
    return getBookList(
        bookRepository.findByIsbnAndAuthorContaining(
            request.isbn(),
            request.author()
        )
    );
  }

  Book createBook(Book book) {
    return bookRepository.save(book);
  }

  void deleteBook(Book book) {
    bookRepository.delete(book);
  }

  private static List<Book> getBookList(List<Book> books) {
    if (books.isEmpty()) {
      throw new BookNotFoundException();
    }

    return books;
  }
}
