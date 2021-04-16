package lendpal.ui;

import lendpal.file.LendPalModelFileHandler;
import lendpal.file.LendPalModelJson;
import lendpal.model.LendPalItem;
import lendpal.model.LendPalItemContainer;
import lendpal.model.LendPalModel;
import lendpal.model.User;

import java.time.Period;
import java.time.ZonedDateTime;

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
        return model.getAvailableItem(itemId);
    }

    @Override
    public Period getDefaultLendTime(String itemId) {
        return model.getDefaultLendTime(itemId);
    }

    @Override
    public LendPalItemContainer getLentItems(String userId) {
        return model.getAllUnavailableItems(userId);
    }

    @Override
    public void lendItem(String userId, LendPalItem item) {
        model.lendAvailableItem(userId, item);
    }

    @Override
    public boolean isItemLent(String itemId) {
        return model.isItemAvailable(itemId);
    }

    @Override
    public void addItem(LendPalItem item) {
        model.addAvailableItem(item);
    }

    @Override
    public void removeItem(LendPalItem item) {
        model.removeAvailableItem(item);
    }

    @Override
    public LendPalItemContainer getAllItems() {
        return model.getAllItems();
    }

    @Override
    public boolean checkUserCredentials(String email, String password) {
        return model.checkUserCredentials(email, password);
    }
}
