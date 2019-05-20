package corbaHistoryServer;

import ChatApp.ChatHistoryMethodsPOA;
import ChatApp.HistoryMessage;

import java.util.LinkedList;

public class HistoryServerImpl extends ChatHistoryMethodsPOA
{
    private LinkedList<HistoryMessage> messageHistory = new LinkedList<>();
    private int historySize;

    public HistoryServerImpl(int historySize)
    {
        this.historySize = historySize;
    }

    @Override
    public HistoryMessage[] getHistory()
    {
        HistoryMessage[] historyMessagesArray = new HistoryMessage[messageHistory.size()];

        synchronized (messageHistory)
        {
            for (int i = 0; i < messageHistory.size(); i++)
            {
                historyMessagesArray[i] = messageHistory.get(i);
            }
            return historyMessagesArray;
        }
    }

    @Override
    public void addMessage(String clientName, String message)
    {
        HistoryMessage msg = new HistoryMessage(clientName, message);

        synchronized (messageHistory)
        {
            if (messageHistory.size() == historySize)
            {
                messageHistory.removeFirst();
                messageHistory.addLast(msg);
            }
            else
            {
                messageHistory.addLast(msg);
            }
        }
    }
}
