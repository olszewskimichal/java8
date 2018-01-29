package lambda.exceptions;

import static lambda.exceptions.LambdaExceptionWrappers.consumerWrapper;
import static lambda.exceptions.LambdaExceptionWrappers.handlingConsumerWrapper;
import static lambda.exceptions.LambdaExceptionWrappers.lambdaWrapper;
import static lambda.exceptions.LambdaExceptionWrappers.throwingConsumerWrapper;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LambdaExceptionWrappersTest {


  private List<Integer> integers;

  @BeforeEach
  public void init() {
    integers = Arrays.asList(3, 9, 7, 0, 10, 20);
  }

  @Test
  public void whenNoExceptionFromLambdaWrapper_thenSuccess() {
    integers.forEach(lambdaWrapper(i -> System.out.println((50 / i))));
  }

  @Test
  public void whenNoExceptionFromConsumerWrapper_thenSuccess() {
    integers.forEach(consumerWrapper(i -> System.out.println(50 / i), ArithmeticException.class));
  }

  @Test
  public void whenExceptionFromThrowingConsumerWrapper_thenSuccess() {
    assertThrows(RuntimeException.class, () -> integers.forEach(throwingConsumerWrapper(this::writeToFile)));
  }

  @Test
  public void whenNoExceptionFromHandlingConsumerWrapper_thenSuccess() {
    integers.forEach(handlingConsumerWrapper(this::writeToFile, IOException.class));
  }

  private void writeToFile(Integer i) throws IOException {
    if (i == 0) {
      throw new IOException(); // mock IOException
    }
  }
}