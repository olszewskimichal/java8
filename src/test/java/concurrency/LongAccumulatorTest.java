package concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.LongAccumulator;
import org.junit.jupiter.api.Test;

class LongAccumulatorTest {

  private static final long A = 1;
  private static final long B = 2;
  private static final long C = 3;
  private static final long D = -4;
  private static final long INITIAL = 0L;

  @Test
  void shouldAddFewNumbers() {
    LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, INITIAL);

    accumulator.accumulate(A);
    accumulator.accumulate(B);
    accumulator.accumulate(C);
    accumulator.accumulate(D);

    assertThat(accumulator.get()).isEqualTo(INITIAL + A + B + C + D);
  }

  @Test
  void shouldReturnMaxFromFewNumbers() {
    LongAccumulator accumulator = new LongAccumulator(Math::max, Integer.MIN_VALUE);

    accumulator.accumulate(A);
    accumulator.accumulate(B);
    accumulator.accumulate(C);
    accumulator.accumulate(D);

    assertThat(accumulator.get()).isEqualTo(C);
  }

  @Test
  void shouldMultiplyFewNumbers() {
    LongAccumulator accumulator = new LongAccumulator((x, y) -> x * y, 1);

    accumulator.accumulate(A);
    accumulator.accumulate(B);
    accumulator.accumulate(C);
    accumulator.accumulate(D);

    assertThat(accumulator.get()).isEqualTo(A*B*C*D);
  }

}
