package date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertLocalDateToDate {

  public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
  }

  public Date convertToDateViaInstant(LocalDate dateToConvert) {
    return java.util.Date.from(dateToConvert.atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant());
  }
}
