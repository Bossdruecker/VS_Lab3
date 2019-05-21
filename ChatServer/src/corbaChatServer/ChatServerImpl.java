package corbaChatServer;

import ChatApp.ChatClient;
import ChatApp.ChatHistoryMethods;
import ChatApp.ChatServerPOA;
import ChatApp.HistoryMessage;
import java.util.HashMap;
import java.util.Map;

public class ChatServerImpl extends ChatServerPOA
{

    static String HISTORY_CMD = "!History";

    ChatHistoryMethods historyServer;
    private final Map<String, ChatClient> clients = new HashMap<>();

    public void setHistoryServer(ChatHistoryMethods historyServer)
    {
        this.historyServer = historyServer;
    }

    @Override
    public boolean login(String clientName, ChatClient chatClient)
    {
        if (clients.containsKey(clientName))
        {
            return false;
        }
        synchronized (clients)
        {
            clients.values().forEach((activeChatClient) -> activeChatClient.receiveLoginMessage(clientName));
            clients.put(clientName, chatClient);
        }
        return true;
    }

    @Override
    public boolean logout(String clientName)
    {
        /*if (!clients.containsKey(clientName))
        {
            return false;
        }*/
        synchronized (clients)
        {
            clients.remove(clientName);
            clients.values().forEach((activeChatClient) -> activeChatClient.receiveLogoutMessage(clientName));
        }
        return true;
    }

    @Override
    public void receiveMessage(String clientName, String chatMessage)
    {

        if (HISTORY_CMD.equals(chatMessage))
        {
            int count = 0;
            String msg = "\n";
            HistoryMessage[] historyArray = historyServer.getHistory();
            while(count < historyArray.length)
            {
                msg = msg + historyArray[count].clientName + ": " + historyArray[count].message + "\n";
                count++;
            }

            ChatClient historyClient = clients.get(clientName);
            synchronized (clients)
            {
                historyClient.receiveMessage("ServerHistory", msg);
            }
            //sonst wird nicht auf beendigung der Funktion gewartet was zur folge hat das der Befehl !History auch in den HistoryServer geschrieben wird
            return;
        }

        historyServer.addMessage(clientName, chatMessage);
        synchronized (clients)
        {
            clients.values().forEach((ChatClient) -> ChatClient.receiveMessage(clientName, chatMessage));
        }
    }
}
