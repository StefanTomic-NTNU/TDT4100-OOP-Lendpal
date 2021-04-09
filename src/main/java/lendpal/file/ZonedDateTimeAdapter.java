package lendpal.file;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeAdapter {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @ToJson
    String toJson(ZonedDateTime time) {
        return time.format(FORMATTER);
    }

    @FromJson
    ZonedDateTime fromJson(String json) {
        return ZonedDateTime.parse(json, FORMATTER);
    }
}
