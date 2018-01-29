package concurrency;

import static concurrency.ConcurrentUtils.stop;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class SynchronizationTest {

  private int count = 0;

  private void increment() {
    count = count + 1;
  }

  private synchronized void incrementSync() {
    count = count + 1;
  }

  @BeforeEach
  void setUp() {
    count = 0;
  }

  @RepeatedTest(100)
  void notSynchronized() {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 10000)
        .forEach(i -> executor.submit(this::increment));

    stop(executor);
    assertThat(count).isNotEqualTo(10000);
  }

  @Test
  void synchronizedEX() {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 10000)
        .forEach(i -> executor.submit(this::incrementSync));

    stop(executor);

    assertThat(count).isEqualTo(10000);
  }

}
