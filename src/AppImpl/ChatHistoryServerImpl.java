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
        for(int i = 0; i < messageHistory.size(); i++)
        {
            historyMessageArray[i] = messageHistory.get(i);
        }
        return historyMessageArray;
    }

    @Override
    public void addMessage(String clientName, String message) {
        HistoryMessage newHistoryMessage = new HistoryMessage(clientName, message);

        messageHistory.addLast(newHistoryMessage);
        if(messageHistory.size() > maxHistorySize)
        {
            messageHistory.removeFirst();
        }
    }
}
