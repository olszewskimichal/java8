package concurrency;

import static concurrency.ConcurrentUtils.stop;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class AtomicVariablesTest {

  @Test
  void atomicIntEx() {
    AtomicInteger atomicInt = new AtomicInteger(0);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 1000)
        .forEach(i -> executor.submit(atomicInt::incrementAndGet));

    stop(executor);
    assertThat(atomicInt.get()).isEqualTo(1000);
  }

  @Test
  void longAdder() {
    LongAdder adder = new LongAdder();
    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 1000)
        .forEach(i -> executor.submit(adder::increment));

    stop(executor);

    assertThat(adder.sumThenReset()).isEqualTo(1000L);
  }

  @Test
  void longAccumulator() {
    LongBinaryOperator op = (x, y) -> 2 * x + y;
    LongAccumulator accumulator = new LongAccumulator(op, 1L);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    IntStream.range(0, 10)
        .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

    ConcurrentUtils.stop(executor);

    assertThat(accumulator.getThenReset()).isEqualTo(2539L);
  }
}
