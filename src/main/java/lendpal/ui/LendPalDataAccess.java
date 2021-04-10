package lendpal.ui;

import lendpal.model.LendPalItem;
import lendpal.model.User;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Map;

public interface LendPalDataAccess {

    public void addUser(User user);

    public boolean containsUser(String userId);

    public User getUser(String userId);

    public User getItemHolder(String itemId);

    public LendPalItem getItem(String itemId);

    public Period getDefaultLendTime(String itemId);

    public ZonedDateTime getReturnDateFromNow(String itemId);

    public Map<String, ZonedDateTime> getLentItems(String userId);

    public void lendItem(String userId, String itemId);

    public boolean isItemLent(String itemId);

    public void addItem(LendPalItem item);

    public void removeItem(LendPalItem item);

    public Map<String, LendPalItem> getAllItems();

    public void writeData();

    public boolean checkUserCredentials(String email, String password);


}
