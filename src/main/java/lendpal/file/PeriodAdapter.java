package lendpal.file;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class PeriodAdapter {

    @ToJson
    String toJson(Period period) {
        return String.format("P%dY%dM%dD",
                period.getYears(),
                period.getMonths(),
                period.getDays());
    }

    @FromJson
    Period fromJson(String json) {
        return Period.parse(json);
    }
}
