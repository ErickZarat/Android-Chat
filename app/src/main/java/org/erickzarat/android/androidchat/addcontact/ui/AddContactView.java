package org.erickzarat.android.androidchat.addcontact.ui;

/**
 * Created by zarathos on 24/06/16
 */
public interface AddContactView {

    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
