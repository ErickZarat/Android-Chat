package org.erickzarat.android.androidchat.chat.ui;

import org.erickzarat.android.androidchat.entities.ChatMessage;

/**
 * Created by zarathos on 24/06/16
 */
public interface ChatView {
    void onMessageReceived(ChatMessage msg);

}
