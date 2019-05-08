package AppImpl;

import ChatApp.ChatClient;
import ChatApp.ChatServerPOA;

public class ChatHistoryServerImpl extends ChatServerPOA {
    @Override
    public boolean login(String clientName, ChatClient chatClient) {
        return false;
    }

    @Override
    public boolean logout(String clientName) {
        return false;
    }

    @Override
    public void receiveMessage(String clientName, String chatMessage) {

    }
}
