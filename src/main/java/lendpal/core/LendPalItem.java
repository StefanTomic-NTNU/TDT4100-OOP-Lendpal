package lendpal.core;
import java.time.LocalDateTime;

public class LendPalItem {

    private String name;
    private String description;
    private LocalDateTime returnDate;

    public LendPalItem(String name, String description, LocalDateTime occupiedUntil) {
        this.name = name;
        this.description = description;
        this.returnDate = occupiedUntil;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}
