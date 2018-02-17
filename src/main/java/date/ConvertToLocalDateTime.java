package date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConvertToLocalDateTime {

  public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

  public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

  public LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
    return new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime();
  }
}
