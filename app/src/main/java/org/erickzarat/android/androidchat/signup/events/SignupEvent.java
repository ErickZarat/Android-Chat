package org.erickzarat.android.androidchat.signup.events;

/**
 * Created by zarathos on 24/06/16
 */
public class SignupEvent {

    public final static int onSignUpSuccess = 0;
    public final static int onSignUpError = 1;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
