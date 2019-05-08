package AppImpl;

import ChatApp.ChatClient;
import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.ChatServerPOA;
import ChatApp.HistoryMessage;

public class ChatServerImpl extends ChatServerPOA {

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
