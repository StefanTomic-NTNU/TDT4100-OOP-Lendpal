package lendpal.model;

import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

public class LendPalItem {

    /**
     * Id is generated using UUID in this iteration of the program.
     * The id is used as key in the LendPalList HashMap. It is not
     * a int since it is easier to convert a UUID to String rather
     * than int.
     */
    private String id;

    private String name;
    private String description;

    /**
     * This is the default lend time of a LendPalItem object,
     * it is not meant to be the default lend time for the entire class,
     * thus it is not static final.
     *
     */
    private Period defaultLendTime;

    /**
     * Supposed to be null when item is available.
     */
    private ZonedDateTime returnDate;

    public LendPalItem(String id, String name, String description, Period defaultLendTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.defaultLendTime = defaultLendTime;
        this.returnDate = null;
    }

    public LendPalItem(String name, String description, Period defaultLendTime) {
        this(UUID.randomUUID().toString(), name, description, defaultLendTime);
    }

    public LendPalItem(String name, String description) {
        this(name, description, Period.ofDays(2));
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getId() {
        return id;
    }

    public boolean idEquals(LendPalItem other) { return this.id.equals(other.id); }

    public Period getDefaultLendTime() {
        return defaultLendTime;
    }

    public void setDefaultLendTime(Period defaultLendTime) {
        this.defaultLendTime = defaultLendTime;
    }

    public void setReturnDate(ZonedDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void lendFor(TemporalAmount time) {
        this.returnDate = ZonedDateTime.now().plus(time);
    }

    public void lendForDefaultTime() {
        lendFor(this.defaultLendTime);
    }

    public void extendLendTime(TemporalAmount time) {
        this.returnDate = this.returnDate.plus(time);
    }

    public void makeItemAvailable() {
        this.returnDate = null;
    }

    public boolean isItemAvailable() {
        return returnDate == null;
    }

    public boolean isReturnDateReached() {
        return this.returnDate.isBefore(ZonedDateTime.now());
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return name + ": " + defaultLendTime.toString();
    }
}
