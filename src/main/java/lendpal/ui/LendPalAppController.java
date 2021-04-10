package lendpal.ui;

public class LendPalAppController {

    private String userId;

    private LendPalDataAccess access;

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
