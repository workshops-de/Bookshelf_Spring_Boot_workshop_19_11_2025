package de.workshops.bookshelf.book;

import static org.hamcrest.Matchers.hasSize;

import de.workshops.bookshelf.config.JacksonTestConfiguration;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookRestController.class)
@AutoConfigureMockMvc
@Import(JacksonTestConfiguration.class)
class BookRestControllerMockitoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    @WithMockUser
    void getAllBooks() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(Collections.singletonList(new Book()));

        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }
}
