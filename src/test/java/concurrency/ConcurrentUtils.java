package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class ConcurrentUtils {

  static void stop(ExecutorService executor) {
    try {
      executor.shutdown();
      executor.awaitTermination(60, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("termination interrupted");
    } finally {
      if (!executor.isTerminated()) {
        System.err.println("killing non-finished tasks");
      }
      executor.shutdownNow();
    }
  }

}
