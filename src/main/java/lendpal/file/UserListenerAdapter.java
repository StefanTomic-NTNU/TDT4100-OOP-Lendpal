package lendpal.file;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import lendpal.model.User;
import lendpal.model.UserListener;

public class UserListenerAdapter {

    @ToJson
    public String toJson(UserListener listener) {
        return "Listener";
    }

    @FromJson
    public UserListener fromJson(String s) {
        if (s.equals("Listener")) {
            return new UserListener() {
                @Override
                public void userChanged(User user) {

                }
            };
        } else {
            return null;
        }
    }
}
