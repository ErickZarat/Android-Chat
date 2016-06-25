package org.erickzarat.android.androidchat.chat;

import org.erickzarat.android.androidchat.chat.events.ChatEvent;

/**
 * Created by zarathos on 24/06/16
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
