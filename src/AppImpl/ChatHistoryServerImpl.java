package AppImpl;

import ChatApp.ChatClient;
import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.ChatServerPOA;
import ChatApp.HistoryMessage;

import java.util.LinkedList;

public class ChatHistoryServerImpl extends ChatHistoryMethodsPOA {

    LinkedList<HistoryMessage> messageHistory = new LinkedList<>();

    @Override
    public HistoryMessage[] getHistory() {
        return new HistoryMessage[0];
    }

    @Override
    public void addMessage(String clientName, String message) {

    }
}
