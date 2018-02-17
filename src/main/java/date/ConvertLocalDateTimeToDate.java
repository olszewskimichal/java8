package date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConvertLocalDateTimeToDate {

  public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
    return java.sql.Timestamp.valueOf(dateToConvert);
  }

  Date convertToDateViaInstant(LocalDateTime dateToConvert) {
    return java.util.Date
        .from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
  }
}
