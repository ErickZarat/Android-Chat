package org.erickzarat.android.androidchat.login;

/**
 * Created by erick on 21/06/16.
 */
public interface LoginRepository {

    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
