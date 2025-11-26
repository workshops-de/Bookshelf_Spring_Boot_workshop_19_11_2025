package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

  @Test
  void createBook(@Autowired BookRepository bookRepository) {
    assertEquals(3, bookRepository.findAll().size());

    Book book = Book.builder()
        .title("Title")
        .author("Author")
        .description("Description")
        .isbn("123-4567890")
        .build();
    bookRepository.save(book);

    List<Book> books = bookRepository.findAll();

    assertNotNull(books);
    assertEquals(4, books.size());
    assertEquals(book.getIsbn(), books.get(3).getIsbn());

    bookRepository.delete(book);

    assertEquals(3, bookRepository.findAll().size());
  }
}
