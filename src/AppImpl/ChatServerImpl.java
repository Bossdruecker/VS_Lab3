package AppImpl;

import ChatApp.*;

import java.util.Map;
import java.util.TreeMap;

public class ChatServerImpl extends ChatServerPOA {

    ChatHistoryMethods historyServer;
    private final Map<String, ChatClient> clientMap = new TreeMap<>();

    public void setHistoryServer(ChatHistoryMethods historyServer)
    {
        this.historyServer = historyServer;
    }

    @Override
    public boolean login(String clientName, ChatClient chatClient) {
        //prüfen ob clientName existiert
        if(clientMap.containsKey(clientName))
        {
            return false;
        }
        else
        {
            clientMap.values().forEach((client -> client.receiveLoginMessage(clientName)));
            clientMap.put(clientName, chatClient);
            return true;
        }
    }

    @Override
    public boolean logout(String clientName) {
        if(clientMap.containsKey(clientName))
        {
            clientMap.remove(clientName);
            clientMap.values().forEach((client -> client.receiveLogoutMessage(clientName)));
            return true;
        }
        return false;
    }

    @Override
    public void receiveMessage(String clientName, String chatMessage) {
        clientMap.values().forEach((client -> client.receiveMessage(clientName, chatMessage)));
        historyServer.addMessage(clientName, chatMessage);
    }
}
