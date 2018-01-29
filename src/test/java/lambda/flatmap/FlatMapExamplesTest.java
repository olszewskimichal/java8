package lambda.flatmap;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlatMapExamplesTest {

  private List<Set<String>> list;

  @BeforeEach
  void setUp() {
    list = new ArrayList<>();
    list.add(Set.of("a", "b", "c"));
    list.add(null);
    list.add(Set.of("abc ", "ab", "cc"));
  }

  @Test
  void shouldReturnCountOfObjectsWhichStartsWithPrefix() {
    long count = list.stream()
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .filter(v -> v.startsWith("a"))
        .count();
    assertThat(count).isEqualTo(3);
  }

  @Test
  void shouldReturnSumOfLengthOfObjectsWhichStartsWithPrefix() {
    int sum = list.stream()
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .filter(value -> value.startsWith("a"))
        .mapToInt(String::length)
        .sum();
    assertThat(sum).isEqualTo(7);
  }

  @Test
  void shouldCollectionTrimObjectsWhichStartsWithPrefix() {
    List<String> sum = list.stream()
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .filter(value -> value.startsWith("a"))
        .map(String::trim)
        .collect(Collectors.toList());
    assertThat(sum).hasSize(3);
  }

}
