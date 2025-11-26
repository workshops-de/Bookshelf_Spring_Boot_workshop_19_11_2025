package de.workshops.bookshelf.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
@Getter
@Setter
public class BookshelfApplicationProperties {

  /**
   * The application title
   */
  private String title;

  /**
   * The application version
   */
  private String version;

  /**
   * The OpenAPI configuration
   */
  private CustomOpenApiConfig customOpenApiConfig;

  @Setter
  @Getter
  public static class CustomOpenApiConfig {

    private boolean enabled;
  }

  private Map<String, BookshelfUser> credentials;
}
