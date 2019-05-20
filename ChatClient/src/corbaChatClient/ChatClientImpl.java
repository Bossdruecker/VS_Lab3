package corbaChatClient;

import ChatApp.ChatClientPOA;

public class ChatClientImpl extends ChatClientPOA
{

    private String nickname = "---";

    public String getNickname()
    {
        return nickname;
    }

    public void setClientName(String nickname)
    {
        this.nickname = nickname;
    }

    @Override
    public void receiveLoginMessage(String nickname)
    {
        System.out.println(nickname + " ist dem Chat beigetreten.");
    }

    @Override
    public void receiveLogoutMessage(String nickname)
    {
        System.out.println(nickname + " hat den Chat verlassen.");
    }

    @Override
    public void receiveMessage(String nickname, String chatMessage)
    {
        System.out.println(nickname + ": " + chatMessage);
    }
}
