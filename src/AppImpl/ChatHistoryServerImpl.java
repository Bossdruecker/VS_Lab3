package AppImpl;

import ChatApp.ChatClient;
import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.ChatServerPOA;
import ChatApp.HistoryMessage;

import java.util.LinkedList;

public class ChatHistoryServerImpl extends ChatHistoryMethodsPOA {

    private final int maxHistorySize;
    LinkedList<HistoryMessage> messageHistory = new LinkedList<>();

    public ChatHistoryServerImpl(int maxHistorySize)
    {
        this.maxHistorySize = maxHistorySize;
    }

    @Override
    public HistoryMessage[] getHistory() {
        HistoryMessage[] historyMessageArray = new HistoryMessage[messageHistory.size()];

    }

    @Override
    public void addMessage(String clientName, String message) {

    }
}
