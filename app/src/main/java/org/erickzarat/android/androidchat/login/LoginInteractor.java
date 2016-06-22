package org.erickzarat.android.androidchat.login;

/**
 * Created by erick on 21/06/16.
 */
public interface LoginInteractor {

    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
