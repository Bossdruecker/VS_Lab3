package AppImpl;

import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.HistoryMessage;

public class ChatServerImpl extends ChatHistoryMethodsPOA {
    @Override
    public HistoryMessage[] getHistory() {
        return new HistoryMessage[0];
    }

    @Override
    public void addMessage(String clientName, String message) {

    }
}
