package de.workshops.bookshelf.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Book {

  private String title;

  private String description;

  private String author;

  private String isbn;
}
