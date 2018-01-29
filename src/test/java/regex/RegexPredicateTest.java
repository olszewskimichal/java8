package regex;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class RegexPredicateTest {

  Predicate<String> emailFilter = Pattern
      .compile("^(.+)@example.com$")
      .asPredicate();

  @Test
  void shouldFilterEmails() {
    List<String> emails = Arrays.asList("alex@example.com", "bob@yahoo.com", "cat@google.com", "david@example.com");

    // Apply predicate filter
    List<String> desiredEmails = emails
        .stream()
        .filter(emailFilter)
        .collect(Collectors.toList());
    assertThat(desiredEmails).hasSize(2);
  }
}
