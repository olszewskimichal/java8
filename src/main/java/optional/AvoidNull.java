package optional;

import java.util.Optional;
import java.util.function.Supplier;

class AvoidNull {

  static <T> Optional<T> resolve(Supplier<T> resolver) {
    try {
      T result = resolver.get();
      return Optional.ofNullable(result);
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }

  static class Outer {

    Nested nested;

    Nested getNested() {
      return nested;
    }
  }

  static class Nested {

    Inner inner;

    Inner getInner() {
      return inner;
    }
  }

  static class Inner {

    String foo;

    String getFoo() {
      return foo;
    }
  }
}
