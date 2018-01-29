package lambda.distinct;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class CustomLambdaDistinctTest {

  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  @Test
  void shouldDistinctPersonsById() {
    Person lokesh = new Person(1, "Lokesh", "Gupta");
    Person brian = new Person(1, "Brian", "Clooney");
    Person alex = new Person(3, "Alex", "Kolen");

    Collection<Person> list = Arrays.asList(lokesh, brian, alex, lokesh, brian, lokesh);

    // Get distinct only
    List<Person> distinctElements = list.stream()
        .filter(distinctByKey(Person::getId))
        .collect(Collectors.toList());

    assertThat(distinctElements).hasSize(2);
  }


  static class Person {

    private Integer id;
    private String fname;
    private String lname;

    public Person(Integer id, String fname, String lname) {
      super();
      this.id = id;
      this.fname = fname;
      this.lname = lname;
    }

    Integer getId() {
      return id;
    }
  }
}
