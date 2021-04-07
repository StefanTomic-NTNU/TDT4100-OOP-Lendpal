package lendpal.model;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

public class LendPalItem {

    private String name;
    private String description;

    /**
     * This is the default lend time of a LendPalItem object,
     * it is not meant to be the default lend time for the entire class,
     * thus it is not static.
     */
    private TemporalAmount defaultLendTime;

    public LendPalItem(String name, String description, TemporalAmount defaultLendTime) {
        this.name = name;
        this.description = description;
        this.defaultLendTime = defaultLendTime;
    }

    public LendPalItem(String name, String description) {
        this(name, description, Period.ofDays(2));
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public TemporalAmount getDefaultLendTime() { return defaultLendTime; }

    public void setDefaultLendTime(TemporalAmount defaultLendTime) { this.defaultLendTime = defaultLendTime; }

    @Override
    public String toString() {
        return name + ": " + defaultLendTime.toString();
    }
}
