package stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class IntStreamTest {

  @Test
  void shouldCalculateSilnia() {
    assertThat(IntStream.range(1, 5).reduce(1, (x, y) -> x * y)).isEqualTo(24); //5!
  }

  @Test
  void shouldReturnMinValueFromStream() {
    assertThat(IntStream.range(1, 5).min().getAsInt()).isEqualTo(1);
  }

  @Test
  void shouldReturnMaxValueFromStream() {
    assertThat(IntStream.range(1, 5).max().getAsInt()).isEqualTo(4);
  }

  @Test
  void shouldTransformIntStreamToStream() {
    Stream<Integer> stream = IntStream.range(1, 5).boxed();
  }

  @Test
  void shouldGenerateStreamOfRandomNumbers() {
    List<Integer> collect = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).boxed().limit(3).collect(Collectors.toList());
    collect.forEach(System.out::println);
  }

  @Test
  void shouldGenerateStreamOfOddNumbers() {
    IntStream limit = IntStream.iterate(0, i -> i + 2).limit(3);
    limit.forEach(System.out::println);
  }


}
