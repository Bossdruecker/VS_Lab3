package corbaChatServer;

import ChatApp.ChatClient;
import ChatApp.ChatHistoryMethods;
import ChatApp.ChatServerPOA;
import ChatApp.HistoryMessage;

import java.util.Map;
import java.util.TreeMap;

public class ChatServerImpl extends ChatServerPOA {

    final String HISTORY_COMMAND = "!History";

    ChatHistoryMethods historyServer;
    private final Map<String, ChatClient> clients = new TreeMap<>();

    public void setHistoryServer(ChatHistoryMethods historyServer) {
        this.historyServer = historyServer;
    }

    @Override
    public boolean login(String clientName, ChatClient chatClient) {
        if (clients.containsKey(clientName)) {
            return false;
        }

        synchronized (clients) {
            clients.values().forEach((activeChatClient) -> activeChatClient.receiveLoginMessage(clientName));
            clients.put(clientName, chatClient);
        }
        return true;
    }

    @Override
    public boolean logout(String clientName) {
        if (!clients.containsKey(clientName)) {
            return false;
        }
        synchronized (clients) {
            clients.remove(clientName);
            clients.values().forEach((activeChatClient) -> activeChatClient.receiveLogoutMessage(clientName));
        }
        return true;
    }

    @Override
    public void receiveMessage(String clientName, String chatMessage) {
        if (HISTORY_COMMAND.equals(chatMessage)) {
            new Thread(() -> {
                String message = "";
                for (HistoryMessage historyMessage : historyServer.getHistory()) {
                    message = message + historyMessage.clientName + ": " + historyMessage.message + "\n";
                }
                final ChatClient clientWhoWantsToSeeHistory = clients.get(clientName);
                synchronized (clients) {
                    clientWhoWantsToSeeHistory.receiveMessage("ServerHistory", message);
                }
            }).start();

            return;
        }

        historyServer.addMessage(clientName, chatMessage);
        synchronized (clients) {
            clients.values().forEach((activeChatClient) -> activeChatClient.receiveMessage(clientName, chatMessage));
        }
    }
}
