package date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertToLocalDate {

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
  }
}
