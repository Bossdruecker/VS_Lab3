package corbaHistoryServer;

import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.HistoryMessage;

import java.util.LinkedList;

public class HistoryServerImpl extends ChatHistoryMethodsPOA {

    private final int maxMessageHistorySize;

    LinkedList<HistoryMessage> messageHistory = new LinkedList<>();

    public HistoryServerImpl(int maxMessageHistorySize) {
        super();
        this.maxMessageHistorySize = maxMessageHistorySize;
    }

    @Override
    public HistoryMessage[] getHistory() {
        synchronized (messageHistory) {
            HistoryMessage[] historyMessagesArray = new HistoryMessage[messageHistory.size()];

            for (int i = messageHistory.size() - 1; i >= 0;
                 i--) {
                historyMessagesArray[i] = messageHistory.get(i);
            }

            return historyMessagesArray;
        }
    }

    @Override
    public void addMessage(String clientName, String message) {
        HistoryMessage newMessage = new HistoryMessage(clientName, message);

        synchronized (messageHistory) {
            messageHistory.addLast(newMessage);

            if (messageHistory.size() > maxMessageHistorySize) {
                messageHistory.removeFirst();
            }
        }
    }
}
