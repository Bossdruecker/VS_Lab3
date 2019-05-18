package corbaChatClient;

import ChatApp.ChatClientPOA;

public class ChatClientImpl extends ChatClientPOA {

    private String clientName = "unknownUser";

    public String getName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void receiveLoginMessage(String clientName) {
        System.out.println(clientName + " ist dem Chat beigetreten.");
    }

    @Override
    public void receiveLogoutMessage(String clientName) {
        System.out.println(clientName + " hat den Chat verlassen.");
    }

    @Override
    public void receiveMessage(String clientName, String chatMessage) {
        System.out.println(clientName + ": " + chatMessage);
    }
}
