package lendpal.core;
import java.time.LocalDateTime;

import java.util.Comparator;

public class LenPalItemComparator implements Comparator<LendPalItem> {

    @Override
    public int compare(LendPalItem o1, LendPalItem o2) {
        return o1.getReturnDate().compareTo(o2.getReturnDate());
    }
}
