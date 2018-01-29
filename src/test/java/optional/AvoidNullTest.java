package optional;

import static optional.AvoidNull.resolve;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Optional;
import optional.AvoidNull.Inner;
import optional.AvoidNull.Nested;
import optional.AvoidNull.Outer;
import org.junit.jupiter.api.Test;

class AvoidNullTest {

  @Test
  void standardNullCheck() {
    Outer outer = new Outer();
    if (outer.nested != null && outer.nested.inner != null) {
      fail("Fail");
    }
  }

  @Test
  void optionalNullCheck() {
    Optional<String> optional = Optional.of(new Outer())
        .map(Outer::getNested)
        .map(Nested::getInner)
        .map(Inner::getFoo);
    assertThat(optional).isNotPresent();
  }

  @Test
  void customOptionalResolverCheck() {
    Optional<String> optional = resolve(() -> new Outer().getNested().getInner().getFoo());
    assertThat(optional).isNotPresent();
  }
}