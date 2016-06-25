package org.erickzarat.android.androidchat.login;

import org.erickzarat.android.androidchat.login.events.LoginEvent;

/**
 * Created by erick on 21/06/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();
    void checkForAuthenticadedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
