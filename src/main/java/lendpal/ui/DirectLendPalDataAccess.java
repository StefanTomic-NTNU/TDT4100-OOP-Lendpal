package lendpal.ui;

import lendpal.file.LendPalModelFileHandler;
import lendpal.file.LendPalModelJson;
import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Map;

public class DirectLendPalDataAccess implements LendPalDataAccess {

    private LendPalModel model;

    private String fileName;

    private LendPalModelFileHandler fileHandler = new LendPalModelJson();

    public DirectLendPalDataAccess(String fileName) {
        this.fileName = fileName;
        readData();
    }

    public void setModel(LendPalModel model) {
        this.model = model;
    }

    public LendPalModel getModel() {
        return this.model;
    }

    public void readData() {
        this.model = fileHandler.load(this.fileName);
    }

    @Override
    public void writeData() {
        fileHandler.save(this.model, this.fileName);
    }

    @Override
    public void addUser(User user) {
        model.addUser(user);
    }

    @Override
    public void addNewUser(String firstName, String lastName, String email, String password, String passwordConfirm) {
        model.addNewUser(firstName, lastName, email, password, passwordConfirm);
    }

    @Override
    public boolean containsUser(String userId) {
        return model.containsUser(userId);
    }

    @Override
    public User getUser(String userId) {
        return model.getUser(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return model.getUserByEmail(email);
    }

    @Override
    public User getItemHolder(String itemId) {
        return model.getItemHolder(itemId);
    }

    @Override
    public LendPalItem getItem(String itemId) {
        return model.getItem(itemId);
    }

    @Override
    public Period getDefaultLendTime(String itemId) {
        return model.getDefaultLendTime(itemId);
    }

    @Override
    public ZonedDateTime getReturnDateFromNow(String itemId) {
        return model.getReturnDateFromNow(itemId);
    }

    @Override
    public Map<String, ZonedDateTime> getLentItems(String userId) {
        return model.getLentItems(userId);
    }

    @Override
    public void lendItem(String userId, String itemId) {
        model.lendItem(userId, itemId);
    }

    @Override
    public boolean isItemLent(String itemId) {
        return model.isItemLent(itemId);
    }

    @Override
    public void addItem(LendPalItem item) {
        model.addItem(item);
    }

    @Override
    public void removeItem(LendPalItem item) {
        model.removeItem(item);
    }

    @Override
    public Map<String, LendPalItem> getAllItems() {
        return model.getAllItems();
    }

    @Override
    public boolean checkUserCredentials(String email, String password) {
        return model.checkUserCredentials(email, password);
    }
}
