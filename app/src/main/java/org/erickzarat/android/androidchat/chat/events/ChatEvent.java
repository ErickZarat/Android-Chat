package org.erickzarat.android.androidchat.chat.events;

import org.erickzarat.android.androidchat.entities.ChatMessage;

/**
 * Created by zarathos on 24/06/16
 */
public class ChatEvent {
    ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
