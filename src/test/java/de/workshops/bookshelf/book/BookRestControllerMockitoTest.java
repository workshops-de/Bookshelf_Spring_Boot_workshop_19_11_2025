package de.workshops.bookshelf.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(BookRestController.class)
class BookRestControllerMockitoTest {

    @Autowired
    private BookRestController bookRestController;

    @MockitoBean
    private BookService bookService;

    @Captor
    ArgumentCaptor<Book> bookCaptor;

    @Test
    void getAllBooks() {
        Mockito.when(bookService.getAllBooks()).thenReturn(Collections.emptyList());

        assertNotNull(bookRestController.getAllBooks());
        assertEquals(0, bookRestController.getAllBooks().size());
    }

  @Test
  void createBook() {
    bookRestController.createBook(
        Book
            .builder()
            .isbn("1234567890")
            .author("Eric Evans")
            .title("Domain-Driven Design")
            .description("This is a book about specific technologies.")
            .build()
    );

    Mockito.verify(bookService).createBook(bookCaptor.capture());
    assertEquals("1234567890", bookCaptor.getValue().getIsbn());
  }
}
