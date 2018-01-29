package lambda.groupingBy;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class GroupingByExamplesTest {

  List<Student> students = Arrays.asList(
      new Student(13, Country.POLAND),
      new Student(15, Country.POLAND),
      new Student(14, Country.GERMANY),
      new Student(14, Country.GERMANY),
      new Student(14, Country.UK),
      new Student(15, Country.UK),
      new Student(14, Country.UK)
  );

  @Test
  void shouldGroupStudentsByCountry() {
    Map<Country, List<Student>> studentsByCountry =
        students.stream()
            .collect(groupingBy(Student::getCountry));
    assertThat(studentsByCountry.get(Country.POLAND)).hasSize(2);
    assertThat(studentsByCountry.get(Country.GERMANY)).hasSize(2);
  }

  @Test
  void shouldCountStudentsByCountry() {
    Map<Country, Long> numberOfStudentsByCountry =
        students.stream()
            .collect(groupingBy(Student::getCountry, counting()));
    assertThat(numberOfStudentsByCountry.get(Country.POLAND)).isEqualTo(2);
    assertThat(numberOfStudentsByCountry.get(Country.GERMANY)).isEqualTo(2);
  }

  @Test
  void shouldReturnMaxAgedStudentPerCountry() {
    Map<Country, Integer> maxAgeByCountry =
        students.stream()
            .collect(Collectors.toMap(Student::getCountry, Student::getAge, BinaryOperator.maxBy(Integer::compare)));
    assertThat(maxAgeByCountry.get(Country.POLAND)).isEqualTo(15);
  }

  public enum Country {POLAND, UK, GERMANY}

  public static class Student {

    private final int age;
    private final Country country;

    Student(int age, Country country) {
      this.age = age;
      this.country = country;
    }

    int getAge() {
      return age;
    }

    Country getCountry() {
      return country;
    }

  }
}
