package lendpal.model;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class LendPalItem {

    private String name;
    private String description;

    /**
     * This is the default lend time of a LendPalItem object,
     * it is not meant to be the default lend time for the entire class,
     * thus it is not static final.
     *
     * The reason for it not being defined as a TemporalAmount is because
     * neither of its main implementations, Period and Duration, are well
     * either too little or too much fine-grained for the purposes of LendPal.
     * Also it is much easier to serialize a long than a TemporalAmount.
     */
    private long defaultDaysLent;

    public LendPalItem(String name, String description, long defaultDaysLent) {
        this.name = name;
        this.description = description;
        this.defaultDaysLent = defaultDaysLent;
    }

    public LendPalItem(String name, String description) {
        this(name, description, (long) 2.0);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public long getDefaultDaysLent() { return defaultDaysLent; }

    public void setDefaultDaysLent(long defaultDaysLent) { this.defaultDaysLent = defaultDaysLent; }

    @Override
    public String toString() {
        return name + ": " + Long.toString(defaultDaysLent);
    }
}
